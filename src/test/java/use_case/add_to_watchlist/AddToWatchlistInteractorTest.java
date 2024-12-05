package use_case.add_to_watchlist;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class AddToWatchlistInteractorTest {

    @Test
    void addMovieToWatchlist() {
        CommonUserWatchlistFactory commonUserWatchlistFactory = new CommonUserWatchlistFactory();
        final UserWatchlist watchlist = commonUserWatchlistFactory.create("Favorites");

        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Nehir", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Nehir");

        user.addWatchlist(watchlist);

        CommonMovieFactory commonMovieFactory = new CommonMovieFactory();
        final Movie movie = commonMovieFactory.create("Inception");

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                assertEquals(1, user.getWatchlist("Favorites").getMovies().size());
                assertEquals(movie, user.getWatchlist("Favorites").getMoviebyName("Inception"));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure view should not be prepared in this scenario.");
            }
        };

        // Create interactor
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(userRepo, presenter);

        // Execute the use case
        interactor.execute(user, 0, movie);
    }

    @Test
    void executeFailWatchlist() {
        CommonUserWatchlistFactory commonUserWatchlistFactory = new CommonUserWatchlistFactory();
        final UserWatchlist watchlist = commonUserWatchlistFactory.create("Favorites");

        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject() {
            @Override
            public boolean saveToWatchlist(User user, int ind, Movie movie) {
                return false;
            }
        };

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Nehir", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Nehir");

        user.addWatchlist(watchlist);

        CommonMovieFactory commonMovieFactory = new CommonMovieFactory();
        final Movie movie = commonMovieFactory.create("Inception");

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                fail("Success view should not be prepared in a failure scenario.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Failed to save movie.", errorMessage);
            }
        };

        // Create interactor
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(userRepo, presenter);

        // Execute the use case
        interactor.execute(user, 0, movie);
    }

    @Test
    void addMovieToPwl() {
        // Set up in-memory data access and stub presenter
        InMemoryUserDataAccessObject dataAccess = new InMemoryUserDataAccessObject();

        CommonUserFactory commonUserFactory = new CommonUserFactory();
        User user = commonUserFactory.create("Nehir", "password");
        dataAccess.save(user);
        dataAccess.setCurrentUsername("Nehir");

        CommonWatchlistFactory commonWatchlistFactory = new CommonWatchlistFactory();
        final Watchlist pwl = commonWatchlistFactory.create();

        user.setPwl(pwl);

        CommonMovieFactory commonMovieFactory = new CommonMovieFactory();
        final Movie movie = commonMovieFactory.create("Inception");

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                assertEquals(1, user.getPwl().getMovies().size());
                assertEquals(movie, user.getPwl().getMoviebyName("Inception"));
            }
            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure view should not be prepared in this scenario.");
            }
        };

        // Create interactor
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(dataAccess, presenter);

        // Execute the use case
        interactor.execute(user, pwl, movie);
    }

    @Test
    void executeFailPwl() {
        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject() {
            @Override
            public boolean saveToPwl(User user, Movie movie) {
                return false;
            }
        };

        CommonUserFactory commonUserFactory = new CommonUserFactory();
        User user = commonUserFactory.create("Nehir", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Nehir");

        CommonWatchlistFactory commonWatchlistFactory = new CommonWatchlistFactory();
        final Watchlist pwl = commonWatchlistFactory.create();

        user.setPwl(pwl);

        CommonMovieFactory commonMovieFactory = new CommonMovieFactory();
        final Movie movie = commonMovieFactory.create("Inception");

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                fail("Success view should not be prepared in a failure scenario.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Failed to save movie.", errorMessage);
            }
        };

        // Create interactor
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(userRepo, presenter);

        // Execute the use case
        interactor.execute(user, pwl, movie);
    }

    @Test
    void addMovieThatIsAlreadyInWatchlist() {
        // Set up in-memory data access and stub presenter
        InMemoryUserDataAccessObject dataAccess = new InMemoryUserDataAccessObject();

        CommonUserFactory commonUserFactory = new CommonUserFactory();
        User user = commonUserFactory.create("Nehir", "password");
        dataAccess.save(user);
        dataAccess.setCurrentUsername("Nehir");

        // Create and add watchlist to the user
        CommonUserWatchlistFactory commonUserWatchlistFactory = new CommonUserWatchlistFactory();
        final UserWatchlist watchlist = commonUserWatchlistFactory.create("Favorites");
        user.addWatchlist(watchlist);

        // Create movie
        CommonMovieFactory commonMovieFactory = new CommonMovieFactory();
        final Movie movie = commonMovieFactory.create("Inception");

        // Add movie to the watchlist first time
        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                assertEquals(1, user.getWatchlist("Favorites").getMovies().size());
                assertEquals(movie, user.getWatchlist("Favorites").getMoviebyName("Inception"));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure view should not be prepared in this scenario.");
            }
        };

        // Create interactor
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(dataAccess, presenter);
        interactor.execute(user, 0, movie); // Add movie once

        // Try to add the same movie again
        AddToWatchlistOutputBoundary duplicateMoviePresenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                fail("Movie should not be added again.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Movie is already in the list: Movie already exists in the watchlist", errorMessage);
            }
        };

        // Create interactor for the second attempt
        AddToWatchlistInputBoundary duplicateMovieInteractor = new AddToWatchlistInteractor(dataAccess, duplicateMoviePresenter);

        // Execute the use case to add the same movie again, expecting failure
        interactor.execute(user, 0, movie);
    }

    @Test
    void addMovieThatIsAlreadyInPwl() {
        // Set up in-memory data access and stub presenter
        InMemoryUserDataAccessObject dataAccess = new InMemoryUserDataAccessObject();

        // Create a user and save it
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        User user = commonUserFactory.create("Nehir", "password");
        dataAccess.save(user);
        dataAccess.setCurrentUsername("Nehir");

        // Create and set Personal Watchlist (PWL) for the user
        CommonWatchlistFactory commonWatchlistFactory = new CommonWatchlistFactory();
        final Watchlist pwl = commonWatchlistFactory.create();
        user.setPwl(pwl);

        // Create movie
        CommonMovieFactory commonMovieFactory = new CommonMovieFactory();
        final Movie movie = commonMovieFactory.create("Inception");

        // Add movie to PWL first time
        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                assertEquals(1, user.getPwl().getMovies().size());
                assertEquals(movie, user.getPwl().getMoviebyName("Inception"));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure view should not be prepared in this scenario.");
            }
        };

        // Create interactor
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(dataAccess, presenter);
        interactor.execute(user, pwl, movie); // Add movie once

        // Try to add the same movie again to PWL, expecting an exception or failure
        AddToWatchlistOutputBoundary duplicateMoviePresenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                fail("Movie should not be added again to the PWL.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Movie is already in this list", errorMessage);
            }
        };

        // Create interactor for the second attempt
        AddToWatchlistInputBoundary duplicateMovieInteractor = new AddToWatchlistInteractor(dataAccess, duplicateMoviePresenter);

        // Execute the use case to add the same movie again to PWL, expecting failure
        try {
            duplicateMovieInteractor.execute(user, pwl, movie);
            fail("Expected exception for duplicate movie, but it was not thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("Movie is already in this list", e.getMessage());
        }
    }


}
