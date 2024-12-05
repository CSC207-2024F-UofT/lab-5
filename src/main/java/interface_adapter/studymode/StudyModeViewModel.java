package interface_adapter.studymode;

import interface_adapter.ViewModel;

/**
 * The View Model for the Study Mode View.
 */
public class StudyModeViewModel extends ViewModel<StudyModeState> {

    public StudyModeViewModel() {
        super("study mode");
        setState(new StudyModeState());
    }
}
