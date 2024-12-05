package use_case.add_to_watchlist;

import entity.*;

/**
 * The add to watchlist Interactor.
 */
public class AddToWatchlistInteractor implements AddToWatchlistInputBoundary {

    private final AddToWatchlistDataAccessInterface userDataAccessObject;
    private final AddToWatchlistOutputBoundary addToWatchlistPresenter;

    public AddToWatchlistInteractor(AddToWatchlistDataAccessInterface userDataAccessObject,
                                    AddToWatchlistOutputBoundary addToWatchlistPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.addToWatchlistPresenter = addToWatchlistPresenter;
    }

    @Override
    public void execute(User user, Watchlist watchlist, Movie movie) {
        try {
            watchlist.addMovie(movie);
        }
        catch (Exception exc) {
            throw new IllegalArgumentException("Movie is already in this list");
        }
        final boolean saveSuccessful = userDataAccessObject.saveToPwl(user, movie);

        if (saveSuccessful) {
            addToWatchlistPresenter.prepareSuccessView(user);
        }
        else {
            addToWatchlistPresenter.prepareFailView("Failed to save movie.");
        }
    }

    @Override
    public void execute(User user, int ind, Movie movie) {
        final UserWatchlist watchlist = user.getWatchlists().get(ind);
        try {
            watchlist.addMovie(movie);
        }
        catch (Exception exc) {
            System.err.println("Movie is already in the list: " + exc.getMessage());
        }

        final boolean saveSuccessful = userDataAccessObject.saveToWatchlist(user, ind, movie);

        if (saveSuccessful) {
            addToWatchlistPresenter.prepareSuccessView(user);
        }
        else {
            addToWatchlistPresenter.prepareFailView("Failed to save movie.");
        }
    }

}
