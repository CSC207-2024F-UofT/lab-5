package use_case.create_watchlist;

public class CreateWatchlistOutputData {
    private String username;
    private boolean useCaseFailed;

    public CreateWatchlistOutputData(String username, boolean useCaseFailed) {
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
