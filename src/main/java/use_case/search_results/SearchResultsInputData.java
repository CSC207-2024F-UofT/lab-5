package use_case.search_results;

import java.util.ArrayList;
import java.util.List;

import data_access.APIMovieAccess;
import entity.CommonMovie;

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
