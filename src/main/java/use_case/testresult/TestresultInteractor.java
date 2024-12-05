package use_case.testresult;

import java.util.ArrayList;

/**
 * This is an interactor for test result use case.
 */
public class TestresultInteractor implements TestresultInputBoundary {
    private TestresultDataAccessInterface testresultDataAccessObject;
    private TestresultOutputBoundary testresultPresenter;

    public TestresultInteractor(TestresultDataAccessInterface testresultDataAccessInterface,
                                TestresultOutputBoundary testresultOutputBoundary) {
        this.testresultDataAccessObject = testresultDataAccessInterface;
        this.testresultPresenter = testresultOutputBoundary;
    }

    @Override
    public void execute(TestresultInputData testresultInputData) {
        // Validate the input data
        if (!isValid(testresultInputData)) {
            testresultPresenter.prepareFailView("Invalid test result provided");
        }
        else {
            final int correctQuestions = testresultInputData.getCorrectQuestions();
            final ArrayList<String> incorrectQuestions = testresultInputData.getIncorrectQuestions();
            // If the input is valid, proceed with prepare success view.
            testresultDataAccessObject.saveCorrectQuestions(correctQuestions);
            testresultDataAccessObject.saveIncorrectQuestions(incorrectQuestions);
            final TestresultOutputData outputData = new TestresultOutputData(correctQuestions, incorrectQuestions);
            testresultPresenter.prepareSuccessView(outputData);
        }
    }

    @Override
    public void switchToLoggedInView() {
        testresultPresenter.switchToLoggedInView();
    }

    // Private method to validate the input data
    private boolean isValid(TestresultInputData testresultInputData) {
        return testresultInputData.getCorrectQuestions() >= 0 && testresultInputData.getIncorrectQuestions() != null;
    }
}
