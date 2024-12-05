package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

/**
 * The controller for the search usecase.
 */
public class SearchController {

    private final SearchInputBoundary searchUseCaseInteractor;

    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;

    }
    /**
     * Method to handle a search request.
     * @param query the query
     */

    public void startSearch(String query) {
        System.out.println(query);
        final SearchInputData inputData = new SearchInputData(query);
        searchUseCaseInteractor.search(inputData);

    }
}

