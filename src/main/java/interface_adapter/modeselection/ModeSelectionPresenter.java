package interface_adapter.modeselection;

import interface_adapter.ViewManagerModel;
import use_case.modeselection.ModeSelectionOutputBoundary;
import use_case.modeselection.ModeSelectionOutputData;

/**
 * Presenter for mode selection use case.
 */
public class ModeSelectionPresenter implements ModeSelectionOutputBoundary {
    private final ModeSelectionViewModel modeSelectionViewModel;
    private final ViewManagerModel viewManagerModel;

    public ModeSelectionPresenter(ViewManagerModel viewManagerModel, ModeSelectionViewModel modeSelectionViewModel) {
        this.modeSelectionViewModel = modeSelectionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ModeSelectionOutputData outputData) {
        modeSelectionViewModel.getState().setSelectedMode(outputData.getSelectedMode());

        viewManagerModel.setState(outputData.getSelectedMode());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // assume there is no failure case when selecting a mode.
    }

    @Override
    public void switchToStudyModeView() {
        viewManagerModel.setState("study mode");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToTestModeView() {
        viewManagerModel.setState("test mode");
        viewManagerModel.firePropertyChanged();
    }
}

