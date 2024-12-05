package entity;

/**
 * The representation of a watchlist in our program.
 */
public interface UserWatchlist extends Watchlist {

    /**
     * Sets the name of the watchlist.
     * @param listname new name of the watchlist
     */
    void changeListName(String listname);

    /**
     * Gets the name of the watchlist.
     * @return list name
     */
    String getListName();
}

