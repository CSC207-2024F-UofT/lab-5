package use_case.search_results;

/**
 * Input Boundary for actions which are related to search results.
 */
public interface SearchResultsInputBoundary {

    /**
     * Executes the switch to movie view use case.
     * @param searchResultsInputData the input data for the search results
     */
    void execute(SearchResultsInputData searchResultsInputData);

    /**
     * Executes the switch to movie view use case.
     */
    void switchToHomeView();

    /**
     * Executes the switch to movie view use case.
     */
    void switchToMovieView();
}
