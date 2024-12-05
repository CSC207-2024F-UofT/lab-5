package use_case.watchlists.rename;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserWatchlist;
import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RenameInteractorTest {

    @Test
    void execute() {
        RenameUserDataAccessInterface dataAccess = new InMemoryUserDataAccessObject();
        RenameOutputBoundary presenter = () -> { };

        User user = new CommonUser("Paul", "pwd");
        final String oldListName = "Favorite movies";
        final String newListName = "Favorite movies1";
        user.getWatchlists().add(new CommonUserWatchlist(oldListName));
        RenameInputData inputData = new RenameInputData(user, 0, newListName);

        RenameInputBoundary interactor = new RenameInteractor(dataAccess, presenter);
        interactor.execute(inputData);
        assertEquals(user.getWatchlists().get(0).getListName(), newListName);
        if (!newListName.equals(user.getWatchlists().get(0).getListName())) {
            fail("Watchlist not renamed");
        }

    }

}