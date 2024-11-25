package use_case.profile;

import use_case.signup.SignupInputData;

public interface ProfileInputBoundary {

    void execute(ProfileInputData profileInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToSavedRecipesView();
}
