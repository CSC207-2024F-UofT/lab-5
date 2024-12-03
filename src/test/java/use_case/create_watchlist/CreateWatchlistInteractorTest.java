package use_case.create_watchlist;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class CreateWatchlistInteractorTest {
    @Test
    void createWatchlistSuccessfully() {

        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Nehir", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Nehir");

        CreateWatchlistOutputBoundary presenter = new CreateWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                assertNotNull(user.getWatchlist("Favorites"));
                assertEquals("Favorites", user.getWatchlist("Favorites").getListName());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure view should not be prepared in a successful scenario.");
            }
        };

        CreateWatchlistInputBoundary interactor = new CreateWatchlistInteractor(userRepo, presenter);
        interactor.execute(user, "Favorites");
    }

    @Test
    void createWatchlistFails() {
        // Arrange

        InMemoryUserDataAccessObject userRepo = new InMemoryUserDataAccessObject() {
            @Override
            public boolean saveWatchlist(User user, UserWatchlist watchlist) {
                return false; // Simulate a failure
            }
        };

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Nehir", "password");
        userRepo.save(user);
        userRepo.setCurrentUsername("Nehir");

        CreateWatchlistOutputBoundary presenter = new CreateWatchlistOutputBoundary() {
            @Override
            public void prepareSuccessView(User user) {
                fail("Success view should not be prepared in a failure scenario.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Failed to save watchlist.", errorMessage);
            }
        };

        CreateWatchlistInputBoundary interactor = new CreateWatchlistInteractor(userRepo, presenter);
        interactor.execute(user, "Favorites");
    }
}
