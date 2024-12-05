package use_case.search_results;

/**
 * The Input Data for the Search Results Use Case.
 */
public class SearchResultsInputData {
    private final String searchText;

    public SearchResultsInputData(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

}
