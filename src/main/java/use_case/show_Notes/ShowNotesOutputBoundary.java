package use_case.show_Notes;

/**
 * Output boundary for show Notes usecase.
 */
public interface ShowNotesOutputBoundary {

    /**
     * Prepares the success view for the show Notes usecase.
     * @param outputData the output data
     */
    void prepareSuccessView(ShowNotesOutputData outputData);

    /**
     * Prepares the fail view for the show Notes usecase.
     * @param errorMessage the message that will be displayed
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to add messsage view.
     * @param discussion the discussion that user chose to view messages for
     */
    void switchToAddMessageView(String discussion);
}
