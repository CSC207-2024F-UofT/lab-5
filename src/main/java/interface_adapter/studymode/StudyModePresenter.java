package interface_adapter.studymode;

import interface_adapter.ViewManagerModel;
import interface_adapter.modeselection.ModeSelectionViewModel;
import interface_adapter.studymodebegin.StudyModeBeginState;
import interface_adapter.studymodebegin.StudyModeBeginViewModel;
import use_case.studymode.StudyModeOutputBoundary;
import use_case.studymode.StudyModeOutputData;

/**
 * The Presenter for the Study Mode Use Case.
 */
public class StudyModePresenter implements StudyModeOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private StudyModeViewModel studyModeViewModel;
    private StudyModeBeginViewModel studyModeBeginViewModel;
    private ModeSelectionViewModel modeSelectionViewModel;

    public StudyModePresenter(ViewManagerModel viewManagerModel,
                              StudyModeViewModel studyModeViewModel,
                              StudyModeBeginViewModel studyModeBeginViewModel,
                              ModeSelectionViewModel modeSelectionViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.studyModeViewModel = studyModeViewModel;
        this.studyModeBeginViewModel = studyModeBeginViewModel;
        this.modeSelectionViewModel = modeSelectionViewModel;
    }

    @Override
    public void prepareSuccessView(StudyModeOutputData outputData) {
        // Get module from the output data and pass it to the StudyModeBeginViewModel
        final StudyModeBeginState studyModeBeginState = studyModeBeginViewModel.getState();
        studyModeBeginState.setModule(outputData.getModule());
        studyModeBeginViewModel.setState(studyModeBeginState);

        studyModeBeginViewModel.firePropertyChanged();

        // Switch to the Study Mode Begin View
        this.viewManagerModel.setState(studyModeBeginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Assume no failure view
    }

    @Override
    public void switchToStudyModeBeginView() {
        viewManagerModel.setState(studyModeBeginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToModeSelectionView() {
        viewManagerModel.setState(modeSelectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
