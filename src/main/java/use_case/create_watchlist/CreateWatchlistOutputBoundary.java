package use_case.create_watchlist;

public interface CreateWatchlistOutputBoundary {
    void prepareSuccessView(CreateWatchlistOutputData outputData);

    void prepareFailView(String errorMessage);
}
