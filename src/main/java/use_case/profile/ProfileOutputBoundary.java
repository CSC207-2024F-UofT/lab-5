package use_case.profile;

import use_case.signup.SignupOutputData;

public interface ProfileOutputBoundary {

    void switchtoSavedrecipeView(ProfileOutputData response);

    void switchToRecipeSearchView();
}
