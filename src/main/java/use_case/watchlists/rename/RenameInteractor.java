package use_case.watchlists.rename;

import entity.User;
import entity.UserFactory;
import entity.UserWatchlist;

/**
 * The Rename Watchlist Interactor.
 */
public class RenameInteractor implements RenameInputBoundary {
    private final RenameUserDataAccessInterface userDataAccessObject;
    private final RenameOutputBoundary userPresenter;

    public RenameInteractor(RenameUserDataAccessInterface userDataAccessObject,
                            RenameOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    /**
     * Executes the rename watchlist use case.
     *
     * @param ranameInputData the input data
     */
    @Override
    public void execute(RenameInputData ranameInputData) {
//        TODO save to the database
        final UserWatchlist watchlist = ranameInputData.getCurrentUser().getWatchlists().get(ranameInputData.getIndex());
        watchlist.changeListName(ranameInputData.getNewListName());
        userPresenter.execute();
    }
}
