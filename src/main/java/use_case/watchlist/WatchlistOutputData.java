package use_case.watchlist;

/**
 * Output Data for the Signup Use Case.
 */
public class WatchlistOutputData {

    private final String username;



    private final boolean useCaseFailed;

    public WatchlistOutputData(String username, boolean useCaseFailed) {
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
