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

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                assertEquals(1, user.getWatchlist("Favorites").getMovies().size());
                assertEquals("Inception", user.getWatchlist("Favorites").getMoviebyName("Inception").getTitle());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure view should not be prepared in this scenario.");
            }
        };

        // Create interactor
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(userRepo, presenter);

        // Execute the use case
        interactor.execute(user, "Favorites", "Inception");
    }

    @Test
    void executeFailWatchlist() {
        CommonUserWatchlistFactory commonUserWatchlistFactory = new CommonUserWatchlistFactory();
        final UserWatchlist watchlist = commonUserWatchlistFactory.create("Favorites");

        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject() {
            @Override
            public boolean saveToWatchlist(User user, String watchlistName, Movie movie) {
                return false;
            }
        };

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Nehir", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Nehir");

        user.addWatchlist(watchlist);

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                fail("Success view should not be prepared in a failure scenario.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Failed to save preferred genres.", errorMessage);
            }
        };

        // Create interactor
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(userRepo, presenter);

        // Execute the use case
        interactor.execute(user, "Favorites", "Inception");
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

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                assertEquals(1, user.getPwl().getMovies().size());
                assertEquals("Inception", user.getPwl().getMoviebyName("Inception").getTitle());
            }
            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure view should not be prepared in this scenario.");
            }
        };

        // Create interactor
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(dataAccess, presenter);

        // Execute the use case
        interactor.execute(user, pwl.getListName(), "Inception");
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

        AddToWatchlistOutputBoundary presenter = new AddToWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                fail("Success view should not be prepared in a failure scenario.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Failed to save preferred genres.", errorMessage);
            }
        };

        // Create interactor
        AddToWatchlistInputBoundary interactor = new AddToWatchlistInteractor(userRepo, presenter);

        // Execute the use case
        interactor.execute(user, "Favorites", "Inception");
    }
}
