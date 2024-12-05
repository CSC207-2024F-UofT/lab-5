package interface_adapter.modeselection;

import interface_adapter.ViewModel;

/**
 * View Model for mode selection use case.
 */
public class ModeSelectionViewModel extends ViewModel<ModeSelectionState> {
    public static final String TITLE_LABEL = "Mode Selection";
    public static final String STUDY_MODE_BUTTON_LABEL = "Study Mode";
    public static final String TEST_MODE_BUTTON_LABEL = "Test Mode";

    public ModeSelectionViewModel() {
        super("logged in");
        setState(new ModeSelectionState());
    }
}
