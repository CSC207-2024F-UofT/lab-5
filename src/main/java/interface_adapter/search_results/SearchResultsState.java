package interface_adapter.search_results;

import entity.Recipe;

import java.util.List;

public class SearchResultsState {

    private List<Recipe> searchResults;

    public void setSearchResults(List<Recipe> searchResults) {
        this.searchResults = searchResults;
    }

    public List<Recipe> getSearchResults() {
        return searchResults;
    }
}
