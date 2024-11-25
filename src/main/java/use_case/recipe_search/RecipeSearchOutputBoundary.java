package use_case.recipe_search;

import use_case.signup.SignupOutputData;

public interface RecipeSearchOutputBoundary {

    /**
     * Prepares the success view for the Saerch Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(RecipeSearchOutputData outputData);

    /**
     * Prepares the failure view for the Saerch Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Search Results View.
     */
    void switchToLoginView();
}
