package use_case.testmode;

/**
 * The Test Mode Interactor.
 */
public class TestModeInteractor implements TestModeInputBoundary {
    private final TestModeOutputBoundary testModeOutputBoundary;

    public TestModeInteractor(TestModeOutputBoundary testModeOutputBoundary) {
        this.testModeOutputBoundary = testModeOutputBoundary;
    }

    @Override
    public void execute() {
        testModeOutputBoundary.prepareSuccessView();
    }

    @Override
    public void switchToTestModeQuestionView() {
        testModeOutputBoundary.switchToTestModeQuestionView();
    }

    @Override
    public void switchToModeSelectionView() {
        testModeOutputBoundary.switchToModeSelectionView();
    }
}
