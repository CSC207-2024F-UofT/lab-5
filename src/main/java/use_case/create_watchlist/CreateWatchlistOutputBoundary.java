package use_case.create_watchlist;

import entity.User;

public interface CreateWatchlistOutputBoundary {

    void prepareSuccessView(User user);

    void prepareFailView(String errorMessage);
}
