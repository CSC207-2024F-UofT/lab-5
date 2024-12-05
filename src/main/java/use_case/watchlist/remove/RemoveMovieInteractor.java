package use_case.watchlist.remove;

import entity.User;

/**
 * The Rename Watchlist Interactor.
 */
public class RemoveMovieInteractor implements RemoveMovieInputBoundary {
    private final RemoveMovieUserDataAccessInterface userDataAccessObject;
    private final RemoveMovieOutputBoundary userPresenter;

    public RemoveMovieInteractor(RemoveMovieUserDataAccessInterface userDataAccessObject,
                                 RemoveMovieOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    /**
     * Executes the remove movie use case.
     *
     * @param removeInputData the input data
     */
    @Override
    public void execute(RemoveMovieInputData removeInputData) {
        final User currentUser = userDataAccessObject.get(removeInputData.getCurrentUser());
        if (removeInputData.getWatchlistIndex() == -1) {
            currentUser.getPwl().getMovies().remove(removeInputData.getIndex());

            userDataAccessObject.deleteFromPwl(currentUser, removeInputData.getIndex());
            userPresenter.execute(currentUser);
        } else {
            currentUser.getWatchlists().get(removeInputData.getWatchlistIndex())
                    .getMovies().remove(removeInputData.getIndex());
            userDataAccessObject.deleteFromWatchlist(currentUser,
                    removeInputData.getWatchlistIndex(), removeInputData.getIndex());
            userPresenter.execute(currentUser, removeInputData.getWatchlistIndex());
        }

    }
}
