package use_case.create_watchlist;

import entity.CommonUserWatchlistFactory;
import entity.User;
import entity.UserWatchlist;

/**
 * Interactor for the create watchlist use case.
 */
public class CreateWatchlistInteractor implements CreateWatchlistInputBoundary {

    private final CreateWatchlistDataAccessInterface createWatchlistDataAccessInterface;
    private final CreateWatchlistOutputBoundary createWatchlistPresenter;
    private final CommonUserWatchlistFactory commonUserWatchlistFactory;

    public CreateWatchlistInteractor(CreateWatchlistDataAccessInterface createWatchlistDataAccessInterface,
                                 CreateWatchlistOutputBoundary createWatchlistPresenter) {
        this.createWatchlistDataAccessInterface = createWatchlistDataAccessInterface;
        this.createWatchlistPresenter = createWatchlistPresenter;
        this.commonUserWatchlistFactory = new CommonUserWatchlistFactory();
    }

    @Override
    public void execute(User user, String watchlistName) {
        final UserWatchlist watchlist = commonUserWatchlistFactory.create(watchlistName);
        user.addWatchlist(watchlist);
        createWatchlistDataAccessInterface.saveWatchlist(user, watchlist);
        createWatchlistPresenter.prepareSuccessView(user);
    }
}
