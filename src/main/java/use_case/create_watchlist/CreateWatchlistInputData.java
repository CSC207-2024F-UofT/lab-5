package use_case.create_watchlist;

import java.util.ArrayList;

import entity.Movie;
import entity.User;

public class CreateWatchlistInputData {
    private final String username;
    private final String watchlistName;
    private final ArrayList<Movie> movies;

    public CreateWatchlistInputData(String username, String watchlistName) {
        this.username = username;
        this.watchlistName = watchlistName;
        this.movies = new ArrayList<>();
    }

    String getWatchlistName() {
        return watchlistName;
    }

    public String getUsername() {
        return username;
    }
}
