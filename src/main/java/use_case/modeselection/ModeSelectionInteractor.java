package use_case.modeselection;

/**
 * The Mode Selection Interactor.
 */
public class ModeSelectionInteractor implements ModeSelectionInputBoundary {
    private final ModeSelectionDataAccessInterface modeSelectionDataAccessObject;
    private final ModeSelectionOutputBoundary presenter;

    public ModeSelectionInteractor(ModeSelectionDataAccessInterface modeSelectionDataAccessInterface,
                                   ModeSelectionOutputBoundary outputBoundary) {
        this.modeSelectionDataAccessObject = modeSelectionDataAccessInterface;
        this.presenter = outputBoundary;
    }

    @Override
    public void execute(ModeSelectionInputData inputData) {
        final String mode = inputData.getSelectedMode();

        if (!isValidMode(mode)) {
            presenter.prepareFailView("Invalid mode selected, Please choose Study or Test");
        }
        else {
            modeSelectionDataAccessObject.saveSelectedMode(mode);
            final ModeSelectionOutputData outputData = new ModeSelectionOutputData(mode);
            presenter.prepareSuccessView(outputData);
        }
    }

    @Override
    public void switchToStudyModeView() {
        presenter.switchToStudyModeView();
    }

    @Override
    public void switchToTestModeView() {
        presenter.switchToTestModeView();
    }

    private boolean isValidMode(String mode) {
        return "study mode".equals(mode) || "test mode".equals(mode);
    }
}
