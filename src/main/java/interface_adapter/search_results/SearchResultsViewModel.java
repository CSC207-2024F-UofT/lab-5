package interface_adapter.search_results;

import interface_adapter.ViewModel;

/**
 * The View Model for the Movie View.
 */
public class SearchResultsViewModel extends ViewModel<SearchResultsState> {
    public static final String TITLE_LABEL = "Search Results";
    public static final String HOME_LABEL = "Home";
    public static final String SEARCH_LABEL = "Search";
    public static final String ENTER_LABEL = "Enter";
    public static final String SEE_MORE_LABEL = "See More Information";

    public SearchResultsViewModel() {
        super("search results");
        setState(new SearchResultsState());
    }
}
