package use_case.recipe_search;

/**
 * The output boundary for the Signup Use Case.
 */
public interface RecipeSearchOutputBoundary {

    /**
     * Prepares the success view for the Search Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(RecipeSearchOutputData outputData);

    /**
     * Prepares the failure view for the Search Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Search Results View.
     */
    void switchToResultsView();

    /**
     * Switches to the Profile View.
     */
    void switchToProfileView();

}
