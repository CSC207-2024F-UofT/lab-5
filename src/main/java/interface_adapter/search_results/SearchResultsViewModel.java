package interface_adapter.search_results;

import interface_adapter.ViewModel;
import view.SearchResultsView;

public class SearchResultsViewModel extends ViewModel<SearchResultsState> {

    public SearchResultsViewModel() {
        super("Results");
        setState(new SearchResultsState());
    }

    public void addPropertyChangeListener(SearchResultsView recipeSearchView) {
    }

}
