package interface_adapter.show_notes;

import use_case.show_Notes.ShowNotesInputBoundary;

/**
 * Controller for the show Notes usecase.
 */
public class ShowNotesController {
    private final ShowNotesInputBoundary showNotesInteractor;

    public ShowNotesController(ShowNotesInputBoundary showNotesInteractor) {
        this.showNotesInteractor = showNotesInteractor;
    }

    /**
     * Executes the show Notes use case.
     */
    public void execute() {
        showNotesInteractor.execute();
    }

    /**
     * Switches to AddMessageView corresponding to the discussion.
     * @param currentNote the discussion chosen by user
     */
    public void switchToAddMessageView(String currentNote) {
        showNotesInteractor.switchToAddMessageView(currentNote);
    }
}
