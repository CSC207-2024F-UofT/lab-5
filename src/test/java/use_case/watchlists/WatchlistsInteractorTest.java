package use_case.watchlists;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import use_case.watchlist.WatchlistUserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;
class WatchlistsInteractorTest {

    @Test
    void switchToHomeView() {
        WatchlistsUserDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        // This creates a successPresenter that tests whether the test case is as we expect.
        WatchlistsOutputBoundary switchToHomeViewPresenter = new WatchlistsOutputBoundary() {

            /**
             * Switches to the Logged In View.
             */
            @Override
            public void switchToHomeView() {

            }

            /**
             * Switches to the Watchlist View.
             *
             * @param currentUser user that is currently logged in
             * @param ind         index that corresponds to the watchlist to switch to
             */
            @Override
            public void switchToWatchlistView(User currentUser, int ind) {
                fail("switchToWatchlistView is not the method that should be called");
            }

            /**
             * Switches to the PWL View.
             *
             * @param currentUser user that is currently logged in
             */
            @Override
            public void switchToPWLView(User currentUser) {
                fail("switchToPWLView is not the method that should be called");
            }
        };

        WatchlistsInputBoundary interactor = new WatchlistsInteractor(dataAccess, switchToHomeViewPresenter,
                new CommonUserFactory());
        interactor.switchToHomeView();
    }

    @Test
    void switchToWatchlistView() {
        WatchlistsUserDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        // This creates a successPresenter that tests whether the test case is as we expect.
        WatchlistsOutputBoundary switchToWatchlistViewPresenter = new WatchlistsOutputBoundary() {

            /**
             * Switches to the Logged In View.
             */
            @Override
            public void switchToHomeView() {
                fail("switchToHomeView is not the method that should be called");
            }

            /**
             * Switches to the Watchlist View.
             *
             * @param currentUser user that is currently logged in
             * @param ind         index that corresponds to the watchlist to switch to
             */
            @Override
            public void switchToWatchlistView(User currentUser, int ind) {
                assertEquals("Paul", currentUser.getName());
                assertEquals(1, ind);
            }

            /**
             * Switches to the PWL View.
             *
             * @param currentUser user that is currently logged in
             */
            @Override
            public void switchToPWLView(User currentUser) {
                fail("switchToPWLView is not the method that should be called");
            }
        };

        WatchlistsInputBoundary interactor = new WatchlistsInteractor(dataAccess, switchToWatchlistViewPresenter,
                new CommonUserFactory());
        User user = new CommonUser("Paul", "pwd");
        interactor.switchToWatchlistView(user, 1);
    }

    @Test
    void switchToPWL() {
        WatchlistsUserDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        // This creates a successPresenter that tests whether the test case is as we expect.
        WatchlistsOutputBoundary switchToPWLViewPresenter = new WatchlistsOutputBoundary() {

            /**
             * Switches to the Logged In View.
             */
            @Override
            public void switchToHomeView() {
                fail("switchToHomeView is not the method that should be called");
            }

            /**
             * Switches to the Watchlist View.
             *
             * @param currentUser user that is currently logged in
             * @param ind         index that corresponds to the watchlist to switch to
             */
            @Override
            public void switchToWatchlistView(User currentUser, int ind) {
                fail("switchToPWLView is not the method that should be called");
            }

            /**
             * Switches to the PWL View.
             *
             * @param currentUser user that is currently logged in
             */
            @Override
            public void switchToPWLView(User currentUser) {
                assertEquals("Paul", currentUser.getName());
            }
        };

        WatchlistsInputBoundary interactor = new WatchlistsInteractor(dataAccess, switchToPWLViewPresenter,
                new CommonUserFactory());
        User user = new CommonUser("Paul", "pwd");
        interactor.switchToPWL(user);
    }
}