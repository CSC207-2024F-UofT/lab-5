package use_case.create_watchlist;

/**
 * Output Data for the create watchlist use case.
 */
public class CreateWatchlistOutputData {

    private final String username;
    private final boolean useCaseFailed;

    public CreateWatchlistOutputData(String username, String watchlistName, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
