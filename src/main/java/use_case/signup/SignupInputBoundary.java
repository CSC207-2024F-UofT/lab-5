package use_case.signup;

/**
 * Input Boundary for actions which are related to signing up.
 */
public interface SignupInputBoundary {

    /**
     * Executes the signup use case.
     * @param signupInputData the input data
     */
    void execute(SignupInputData signupInputData);

    /**
     * Executes the switch to Survey1View use case.
     * @param uname newly created user
     */
    void switchToSurvey1View(String uname);

    /**
     * Executes the switch to LoginView use case.
     */
    void switchToLoginView();
}
