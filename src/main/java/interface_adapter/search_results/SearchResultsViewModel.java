package interface_adapter.search_results;

import interface_adapter.ViewModel;
import view.SearchResultsView;

public class SearchResultsViewModel extends ViewModel<SearchResultsState> {

    public SearchResultsViewModel() {
        super("search results");
        setState(new SearchResultsState());
    }

    public void addPropertyChangeListener(SearchResultsView recipeSearchView) {
    }
}
