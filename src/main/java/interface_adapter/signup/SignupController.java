package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Signup Use Case.
 */
public class SignupController {

    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username to sign up
     * @param password1 the password
     * @param password2 the password repeated
     */
    public void execute(String username, String password1, String password2) {
        final SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to Survey1" Use Case.
     * @param uname newly created user
     */
    public void switchToSurvey1View(String uname) {
        this.userSignupUseCaseInteractor.switchToSurvey1View(uname);
    }

    /**
     * Executes the "switch to Login" Use Case.
     */
    public void switchToLoginView() {
        this.userSignupUseCaseInteractor.switchToLoginView();
    }
}
