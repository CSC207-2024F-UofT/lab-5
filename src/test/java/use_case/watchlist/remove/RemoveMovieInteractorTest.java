package use_case.watchlist.remove;

import entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveMovieInteractorTest {

    @Test
    void execute() {
        RemoveMovieUserDataAccessInterface dataAccessInterface = new RemoveMovieUserDataAccessInterface() {
            CommonUser alice = new CommonUser("Alice", "pwd");


            @Override
            public boolean existsByName(String username) {
                return true;
            }

            @Override
            public void save(User user) {

            }

            @Override
            public boolean deleteWatchlist(User user, int ind) {
                return true;
            }

            @Override
            public User get(String username) {
                CommonMovie homeAlone = new CommonMovie("Home Alone");
                alice.getPwl().getMovies().add(homeAlone);
                UserWatchlist watchlist = new CommonUserWatchlist("Favs");
                watchlist.getMovies().add(homeAlone);
                alice.getWatchlists().add(watchlist);
                return alice;
            }

            @Override
            public boolean deleteFromWatchlist(User user, int watchlistInd, int ind) {
                return true;
            }

            @Override
            public boolean deleteFromPwl(User user, int ind) {
                return true;
            }
        };

        RemoveMovieOutputBoundary presenter = new RemoveMovieOutputBoundary() {
            @Override
            public void execute(User currentUser) {

            }
            @Override
            public void execute(User currentUser, int ind) {

            }
        };

        CommonUser alice = new CommonUser("Alice", "pwd");
        CommonMovie homeAlone = new CommonMovie("Home Alone");
        alice.getPwl().getMovies().add(homeAlone);
        UserWatchlist watchlist = new CommonUserWatchlist("Favs");
        watchlist.getMovies().add(homeAlone);
        alice.getWatchlists().add(watchlist);
        RemoveMovieInteractor interactor = new RemoveMovieInteractor(dataAccessInterface, presenter);
        RemoveMovieInputData inputData = new RemoveMovieInputData(alice.getName(), -1, 0);
        interactor.execute(inputData);
        RemoveMovieInputData inputData1 = new RemoveMovieInputData(alice.getName(), 0, 0);
        interactor.execute(inputData1);

    }
}