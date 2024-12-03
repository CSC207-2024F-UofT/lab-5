package entity;

public class CommonUserWatchlistFactory {

    public UserWatchlist create(String listName) {
        return new CommonUserWatchlist(listName);
    }
}
