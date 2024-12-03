package use_case.add_to_watchlist;

public class AddToWatchlistOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public AddToWatchlistOutputData(String username, String movieTitle, boolean useCaseFailed) {
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