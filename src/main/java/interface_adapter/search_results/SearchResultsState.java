package interface_adapter.search_results;

import java.util.List;

import entity.Recipe;

/**
 * The state for the Search Results View Model.
 */
public class SearchResultsState {

    private List<Recipe> searchResults;

    public void setSearchResults(List<Recipe> searchResults) {
        this.searchResults = searchResults;
    }

    public List<Recipe> getSearchResults() {
        return searchResults;
    }
}
