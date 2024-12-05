package use_case.watchlist;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WatchlistInteractorTest {

    @Test
    void switchToHomeViewTest() {
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
            public void switchToMovieSearchView(String currentUser) {
                fail("switchToMovieSearchView is not the method that should be called");
            }

            @Override
            public void switchToMovieView(User aCurrentUser, Movie aCurrentMovie, boolean watchedData) {
                fail("switchToMovieView is not the method that should be called");
            }
        };

        WatchlistInputBoundary interactor = new WatchlistInteractor(dataAccess, homeViewPresenter);
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
            public void switchToMovieSearchView(String currentUser) {
                fail("switchToMovieSearchView is not the method that should be called");
            }
            @Override
            public void switchToMovieView(User aCurrentUser, Movie aCurrentMovie, boolean watchedData) {
                fail("switchToMovieView is not the method that should be called");
            }
        };

        WatchlistInputBoundary interactor = new WatchlistInteractor(dataAccess, watchlistsViewPresenter);
        interactor.switchToWatchlistsView("Alice");
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
            public void switchToMovieSearchView(String currentUser) {
                assertEquals("Alice", currentUser);
            }

            @Override
            public void switchToMovieView(User aCurrentUser, Movie aCurrentMovie, boolean watchedData) {
                fail("switchToMovieView is not the method that should be called");
            }
        };

        WatchlistInputBoundary interactor = new WatchlistInteractor(dataAccess, movieSearchPresenter);
        interactor.switchToMovieSearchView("Alice");
    }

    @Test
    void switchToMovieView() {
        WatchlistUserDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        CommonUser alice = new CommonUser("Alice", "pwd");
        dataAccess.save(alice);
        CommonMovie homeAlone = new CommonMovie("Home Alone");
        alice.getPwl().getMovies().add(homeAlone);

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
            public void switchToMovieSearchView(String currentUser) {
                fail("switchToMovieSearchView is not the method that should be called");
            }

            @Override
            public void switchToMovieView(User aCurrentUser, Movie aCurrentMovie, boolean watchedData) {
                assertEquals("Alice", aCurrentUser.getName());

            }
        };

        WatchlistInputBoundary interactor = new WatchlistInteractor(dataAccess, movieSearchPresenter);
        interactor.switchToMovieView(alice.getName(), homeAlone, 0);
    }
}
