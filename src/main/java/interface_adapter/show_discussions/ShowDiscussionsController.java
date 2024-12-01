package interface_adapter.show_discussions;

import use_case.show_discussions.ShowDiscussionsInputBoundary;

/**
 * Controller for the show discussions usecase.
 */
public class ShowDiscussionsController {
    private final ShowDiscussionsInputBoundary showTopicsInteractor;

    public ShowDiscussionsController(ShowDiscussionsInputBoundary showDiscussionsInteractor) {
        this.showTopicsInteractor = showDiscussionsInteractor;
    }

    /**
     * Executes the show discussions use case.
     */
    public void execute() {
        showTopicsInteractor.execute();
    }

    /**
     * Switches to AddMessageView corresponding to the discussion.
     * @param currentDiscussion the discussion chosen by user
     */
    public void switchToAddMessageView(String currentDiscussion) {
        showTopicsInteractor.switchToAddMessageView(currentDiscussion);
    }
}
