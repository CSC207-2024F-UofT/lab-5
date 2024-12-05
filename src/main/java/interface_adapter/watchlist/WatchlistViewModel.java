package interface_adapter.watchlist;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Wacthlists View.
 */
public class WatchlistViewModel extends ViewModel<WatchlistState> {

    public static final String BACK_LABEL = "Back";
    public static final String HOME_LABEL = "Home";
    public static final String ADD_MOVIE_LABEL = "Add a movie";

    public static final String REMOVE_LABEL = "remove";

    public WatchlistViewModel() {
        super("watchlist");
        setState(new WatchlistState());
    }

}
