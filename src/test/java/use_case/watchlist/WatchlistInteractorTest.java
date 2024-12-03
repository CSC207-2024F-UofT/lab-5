package use_case.watchlist;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WatchlistInteractorTest {

    @Test
    void switchToHomeView() {
        WatchlistUserDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        WatchlistOutputBoundary homeViewPresenter = new WatchlistOutputBoundary() {

            @Override
            public void switchToHomeView() {
                // This method should be called in the test case.
            }

            @Override
            public void switchToWatchlistsView(User currentUser) {
                fail("switchToWatchlistsView is not the method that should be called");
            }

            @Override
            public void switchToPWLView(User currentUser) {
                fail("switchToPWLView is not the method that should be called");
            }

            @Override
            public void switchToMovieSearchView(User currentUser) {
                fail("switchToMovieSearchView is not the method that should be called");
            }
        };

        WatchlistInputBoundary interactor = new WatchlistInteractor(dataAccess, homeViewPresenter, new CommonUserFactory());
        interactor.switchToHomeView();
    }

    @Test
    void switchToWatchlistsView() {
        WatchlistUserDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        dataAccess.save(new CommonUser("Alice", "pwd"));

        WatchlistOutputBoundary watchlistsViewPresenter = new WatchlistOutputBoundary() {

            @Override
            public void switchToHomeView() {
                fail("switchToHomeView is not the method that should be called");
            }

            @Override
            public void switchToWatchlistsView(User currentUser) {
                assertEquals("Alice", currentUser.getName());
            }

            @Override
            public void switchToPWLView(User currentUser) {
                fail("switchToPWLView is not the method that should be called");
            }

            @Override
            public void switchToMovieSearchView(User currentUser) {
                fail("switchToMovieSearchView is not the method that should be called");
            }
        };

        WatchlistInputBoundary interactor = new WatchlistInteractor(dataAccess, watchlistsViewPresenter, new CommonUserFactory());
        interactor.switchToWatchlistsView("Alice");
    }

    @Test
    void switchToPWLView() {
        WatchlistUserDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        WatchlistOutputBoundary pwlViewPresenter = new WatchlistOutputBoundary() {

            @Override
            public void switchToHomeView() {
                fail("switchToHomeView is not the method that should be called");
            }

            @Override
            public void switchToWatchlistsView(User currentUser) {
                fail("switchToWatchlistsView is not the method that should be called");
            }

            @Override
            public void switchToPWLView(User currentUser) {
                assertEquals("Alice", currentUser.getName());
            }

            @Override
            public void switchToMovieSearchView(User currentUser) {
                fail("switchToMovieSearchView is not the method that should be called");
            }
        };

        WatchlistInputBoundary interactor = new WatchlistInteractor(dataAccess, pwlViewPresenter, new CommonUserFactory());
        User user = new CommonUser("Alice", "pwd");
        interactor.switchToPWL(user);
    }

    @Test
    void switchToMovieSearchView() {
        WatchlistUserDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        dataAccess.save(new CommonUser("Alice", "pwd"));

        WatchlistOutputBoundary movieSearchPresenter = new WatchlistOutputBoundary() {

            @Override
            public void switchToHomeView() {
                fail("switchToHomeView is not the method that should be called");
            }

            @Override
            public void switchToWatchlistsView(User currentUser) {
                fail("switchToWatchlistsView is not the method that should be called");
            }

            @Override
            public void switchToPWLView(User currentUser) {
                fail("switchToPWLView is not the method that should be called");
            }

            @Override
            public void switchToMovieSearchView(User currentUser) {
                assertEquals("Alice", currentUser.getName());
            }
        };

        WatchlistInputBoundary interactor = new WatchlistInteractor(dataAccess, movieSearchPresenter, new CommonUserFactory());
        interactor.switchToMovieSearchView("Alice");
    }
}
