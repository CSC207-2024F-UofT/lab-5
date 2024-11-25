package use_case.profile;

import use_case.signup.SignupOutputData;

public interface ProfileOutputBoundary {

    void prepareSuccessView(ProfileOutputData outputData);

    void switchtoSavedrecipeView();
}
