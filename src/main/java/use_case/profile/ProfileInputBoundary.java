package use_case.profile;

import use_case.signup.SignupInputData;

public interface ProfileInputBoundary {

    /**
     * Executes the switch to login view use case.
     */
    void switchToSavedRecipesView(ProfileInputData profileInputData);

    void switchToRecipeSearchView();
}
