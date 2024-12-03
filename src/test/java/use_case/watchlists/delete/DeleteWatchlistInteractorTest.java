package use_case.watchlists.delete;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserWatchlist;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.watchlists.rename.*;

import static org.junit.jupiter.api.Assertions.*;

class DeleteWatchlistInteractorTest {

    @Test
    void execute() {
        DeleteWatchlistUserDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        DeleteWatchlistOutputBoundary presenter = () -> { };

        User user = new CommonUser("Paul", "pwd");
        final String oldListName = "Favorite movies";
        user.getWatchlists().add(new CommonUserWatchlist(oldListName));
        assertEquals(user.getWatchlists().size(), 1);
        DeleteWatchlistInputData inputData = new DeleteWatchlistInputData(user, 0);

        DeleteWatchlistInputBoundary interactor = new DeleteWatchlistInteractor(dataAccess, presenter);
        interactor.execute(inputData);
        assertEquals(user.getWatchlists().size(), 0);

    }
}