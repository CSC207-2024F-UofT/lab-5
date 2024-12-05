package use_case.search_results;

import entity.CommonMovie;
import entity.User;

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
     * @param currentUser the current user
     * @param searchResult the current searched movie
     * @param watched if the movie has been watched
     */
    void switchToMovieView(User currentUser, CommonMovie searchResult, boolean watched);

    /**
     * Prepares the success view for the Search results Case.
     * @param searchResultsOutputData the output data
     */
    void prepareSuccessView(SearchResultsOutputData searchResultsOutputData);
}
