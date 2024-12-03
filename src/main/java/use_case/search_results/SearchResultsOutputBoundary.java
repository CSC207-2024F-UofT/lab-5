package use_case.search_results;

/**
 * The output boundary for the Search Results Use Case.
 */
public interface SearchResultsOutputBoundary {

    /**
     * Prepares the failure view for the Search results Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Home View.
     */
    void switchToHomeView();

    /**
     * Switches to the Movie View.
     */
    void switchToMovieView();

    /**
     * Prepares the success view for the Search results Case.
     * @param searchResultsOutputData the output data
     */
    void prepareSuccessView(SearchResultsOutputData searchResultsOutputData);
}
