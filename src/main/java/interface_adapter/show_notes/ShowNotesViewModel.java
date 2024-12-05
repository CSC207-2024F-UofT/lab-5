package interface_adapter.show_notes;

import interface_adapter.ViewModel;

/**
 * The view model for the show Notes view.
 */
public class ShowNotesViewModel extends ViewModel<ShowNotesState> {
    public static final String TITLE_LABEL = "Notes in ";
    public static final String VIEW_MESSAGES_BUTTON_LABEL = "View messages";
    public static final String NEW_NOTE_BUTTON_LABEL = "New Note";

    public ShowNotesViewModel() {
        super("show Notes");
        setState(new ShowNotesState());
    }
}
