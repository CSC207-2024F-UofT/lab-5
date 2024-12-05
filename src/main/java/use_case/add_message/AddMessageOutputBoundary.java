package use_case.add_message;

/**
 * Output boundary for the add message use case.
 */
public interface AddMessageOutputBoundary {
    /**
     * Prepares view for showing new message.
     * @param outputData output data.
     */
    void prepareShowMessageView(AddMessageOutputData outputData);
}
