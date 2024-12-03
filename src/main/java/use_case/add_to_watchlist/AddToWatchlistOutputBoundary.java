package use_case.add_to_watchlist;

import entity.User;

public interface AddToWatchlistOutputBoundary {

    void prepareSuccessView(User user);

    void prepareFailView(String errorMessage);
}
