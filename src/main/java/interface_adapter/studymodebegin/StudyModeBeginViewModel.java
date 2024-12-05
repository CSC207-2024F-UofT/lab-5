package interface_adapter.studymodebegin;

import interface_adapter.ViewModel;

/**
 * The View Model for the Study Mode Begin View.
 */
public class StudyModeBeginViewModel extends ViewModel<StudyModeBeginState> {

    public StudyModeBeginViewModel() {
        super("study mode begin");
        setState(new StudyModeBeginState());
    }
}
