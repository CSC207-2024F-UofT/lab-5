package use_case.watchlist;

import entity.User;

/**
 * Input Boundary for actions which are related to watchlists.
 */
public interface WatchlistInputBoundary {

    /**
     * Executes the switch to home view use case.
     */
    void switchToHomeView();

    /**
     * Executes the switch to PWL view use case.
     * @param currentUser user that is currently logged in
     */
    void switchToPWL(User currentUser);

    /**
     * Executes the switch to watchlists view use case.
     * @param username name of the user that is currently logged in
     */
    void switchToWatchlistsView(String username);

}
