package interface_adapter.watchlists;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Wacthlists View.
 */
public class WatchlistsViewModel extends ViewModel<WatchlistsState> {

    public static final String TITLE_LABEL = "My Watchlists";
    public static final String HOME_LABEL = "Home";
    public static final String CREATE_LIST_LABEL = "Create new watchlist";
    public static final String PWL_LABEL = "Previously watched";

    public static final String RENAME_LABEL = "rename";

    public static final String DELETE_LABEL = "delete";

    public WatchlistsViewModel() {
        super("watchlists");
        setState(new WatchlistsState());
    }

}
