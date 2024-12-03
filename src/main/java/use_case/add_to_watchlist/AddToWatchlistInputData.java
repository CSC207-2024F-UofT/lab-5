package use_case.add_to_watchlist;

public class AddToWatchlistInputData {
    private final String username;
    private final String movieTitle;

    public AddToWatchlistInputData(String username, String movieTitle) {
        this.username = username;
        this.movieTitle = movieTitle;
    }

    public String getUsername() {
        return username;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
}
