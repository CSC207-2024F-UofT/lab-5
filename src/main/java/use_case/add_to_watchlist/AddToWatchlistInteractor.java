package use_case.add_to_watchlist;

import entity.*;

/**
 * The add to watchlist Interactor.
 */
public class AddToWatchlistInteractor implements AddToWatchlistInputBoundary {

    private final AddToWatchlistDataAccessInterface userDataAccessObject;
    private final AddToWatchlistOutputBoundary addToWatchlistPresenter;
    private final CommonMovieFactory commonMovieFactory;

    public AddToWatchlistInteractor(AddToWatchlistDataAccessInterface userDataAccessObject,
                                    AddToWatchlistOutputBoundary addToWatchlistPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.addToWatchlistPresenter = addToWatchlistPresenter;
        this.commonMovieFactory = new CommonMovieFactory();
    }

    @Override
    public void execute(User user, String watchlistName, String movieTitle) {
        final Movie movie = commonMovieFactory.create(movieTitle);

        try {
            user.getWatchlist(watchlistName).addMovie(movie);
        }
        catch (Exception e) {
            System.err.println("Movie is already in the list: " + e.getMessage());
        }

        if (watchlistName == user.getPwl().getListName()) {
            userDataAccessObject.saveToPwl(user, movie);
        }
        else {
            userDataAccessObject.saveToWatchlist(user, watchlistName, movie);
        }
        addToWatchlistPresenter.prepareSuccessView(user);
    }

}