package use_case.testresult;

/**
 * Input Boundary for the Test Result use case.
 */
public interface TestresultInputBoundary {
    /**
     * Execute the test result processing use case.
     * @param inputdata Data containing test result details.
     */
    void execute(TestresultInputData inputdata);

    /**
     * Executes the switch to mode selection use case.
     */
    void switchToLoggedInView();
}
