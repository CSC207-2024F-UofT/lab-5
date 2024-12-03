package use_case.MyReviews;

import entity.CommonMovieReview;
import entity.CommonUser;
import entity.MovieReview;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.my_reviews.MyReviewsPresenter;
import interface_adapter.my_reviews.MyReviewsViewModel;
import use_case.my_reviews.*;
import data_access.InMemoryUserDataAccessObject;

import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyReviewsInteractorTest {
    private InMemoryUserDataAccessObject dataAccess;
    private User testUser;
    MyReviewsViewModel viewModel = new MyReviewsViewModel();
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    MyReviewsOutputBoundary presenter = new MyReviewsPresenter(viewModel, viewManagerModel);

    @Before
    public void setUp() {
        dataAccess = new InMemoryUserDataAccessObject();
        testUser = new CommonUser("Bob", "password");

        // Save the user and add reviews
        dataAccess.save(testUser);
        dataAccess.addReview(testUser, new CommonMovieReview("Bob", new Date(), 4.5, "Great movie!", "movie1"));
        dataAccess.addReview(testUser, new CommonMovieReview("Bob", new Date(), 5.0, "Amazing!", "movie2"));
    }

    @Test
    public void testFetchReviewsSuccess() {
        MyReviewsViewModel viewModel = new MyReviewsViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        // Set up the in-memory data access object
        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject();

        // Create a test user
        User testUser = new CommonUser("Bob", "password");
        userRepo.save(testUser);

        // Add reviews for the test user
        MovieReview review1 = new CommonMovieReview("Bob", new Date(), 4.5, "Great movie!", "Movie1");
        MovieReview review2 = new CommonMovieReview("Bob", new Date(), 5.0, "Amazing!", "Movie2");
        userRepo.addReview(testUser, review1);
        userRepo.addReview(testUser, review2);

        // Create a presenter
        MyReviewsOutputBoundary presenter = new MyReviewsPresenter(viewModel, viewManagerModel) {
            @Override
            public void prepareMyReviewsView(MyReviewsOutputData outputData) {
                // Verify the reviews returned to the presenter
                List<MovieReview> reviews = outputData.getReviews();
                assertEquals(2, reviews.size());
                assertEquals("Movie1", reviews.get(0).getMovieTitle());
                assertEquals("Great movie!", reviews.get(0).getContent());
                assertEquals(4.5, reviews.get(0).getStarRating(), 0.01);
                assertEquals("Movie2", reviews.get(1).getMovieTitle());
                assertEquals("Amazing!", reviews.get(1).getContent());
            }

            @Override
            public void prepareNoReviewsView(String message) {
                throw new AssertionError("No reviews view should not be triggered for this test.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                throw new AssertionError("Fail view should not be triggered for this test.");
            }
        };

        // Create the interactor
        MyReviewsInteractor interactor = new MyReviewsInteractor(userRepo, presenter);

        // Execute the interactor with the test user
        interactor.execute(testUser);
    }

    @Test
    public void testNoReviewsView() {
        // Set up the in-memory data access object
        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject();

        // Create a test user with no reviews
        User testUser = new CommonUser("Alice", "password");
        userRepo.save(testUser);

        // Create a presenter
        MyReviewsOutputBoundary presenter = new MyReviewsPresenter(viewModel, viewManagerModel) {
            @Override
            public void prepareMyReviewsView(MyReviewsOutputData outputData) {
                throw new AssertionError("MyReviews view should not be triggered for this test.");
            }

            @Override
            public void prepareNoReviewsView(String message) {
                // Verify the correct message is passed
                assertEquals("No reviews yet.", message);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                throw new AssertionError("Fail view should not be triggered for this test.");
            }
        };

        // Create the interactor
        MyReviewsInteractor interactor = new MyReviewsInteractor(userRepo, presenter);

        // Execute the interactor with the test user
        interactor.execute(testUser);

        // Verify the user's reviews list is empty
        List<MovieReview> reviews = userRepo.getReviews(testUser);
        assertTrue(reviews.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteWithNullUser() {
        // Create a presenter
        MyReviewsOutputBoundary presenter = new MyReviewsPresenter(viewModel, viewManagerModel) {
            @Override
            public void prepareMyReviewsView(MyReviewsOutputData outputData) {
                throw new AssertionError("MyReviews view should not be triggered for this test.");
            }

            @Override
            public void prepareNoReviewsView(String message) {
                throw new AssertionError("NoReviews view should not be triggered for this test.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                throw new AssertionError("Fail view should not be triggered for this test.");
            }
        };

        // Create the interactor
        MyReviewsInteractor interactor = new MyReviewsInteractor(dataAccess, presenter);

        // Execute the interactor with a null user
        interactor.execute(null);
    }


    @Test
    public void testGoBack() {
        // Create a custom implementation of MyReviewsOutputBoundary for testing
        class TestPresenter implements MyReviewsOutputBoundary {
            private boolean goBackCalled = false;

            @Override
            public void prepareMyReviewsView(MyReviewsOutputData outputData) {
                throw new AssertionError("MyReviews view should not be triggered for this test.");
            }

            @Override
            public void prepareNoReviewsView(String message) {
                throw new AssertionError("NoReviews view should not be triggered for this test.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                throw new AssertionError("Fail view should not be triggered for this test.");
            }

            @Override
            public void goBack() {
                goBackCalled = true; // Track that goBack was called
            }

            public boolean isGoBackCalled() {
                return goBackCalled;
            }
        }

        // Create an instance of the test presenter
        TestPresenter testPresenter = new TestPresenter();

        // Create the interactor
        MyReviewsInteractor interactor = new MyReviewsInteractor(dataAccess, testPresenter);

        // Call the goBack method
        interactor.goBack();

        // Assert that goBack was called on the presenter
        assertTrue("goBack() should have been called.", testPresenter.isGoBackCalled());
    }
    }
