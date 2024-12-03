package use_case.watchlist;

/**
 * The Input Data for the Signup Use Case.
 */
public class WatchlistInputData {

    private final String username;
    private final String password;

    public WatchlistInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

}
