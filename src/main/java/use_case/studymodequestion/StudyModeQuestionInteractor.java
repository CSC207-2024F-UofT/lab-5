package use_case.studymodequestion;

/**
 * The TestModeQuestion Interactor.
 */
public class StudyModeQuestionInteractor implements StudyModeQuestionInputBoundary {
    private final StudyModeQuestionDataAccessInterface smqdaiObject;
    private final StudyModeQuestionOutputBoundary presenter;

    public StudyModeQuestionInteractor(StudyModeQuestionDataAccessInterface smqdaiObject,
                                   StudyModeQuestionOutputBoundary outputBoundary) {
        this.smqdaiObject = smqdaiObject;
        this.presenter = outputBoundary;
    }

    @Override

    public void execute(StudyModeQuestionInputData input) {
        final StudyModeQuestionOutputData output = new StudyModeQuestionOutputData();
        presenter.prepareSuccessView(output);
    }

    /**
     * Switch back to the StudyMode View.
     */
    @Override
    public void switchToStudyModeView() {
        presenter.switchToStudyMode();
    }
}
