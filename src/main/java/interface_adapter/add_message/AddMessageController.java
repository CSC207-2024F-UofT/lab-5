package interface_adapter.add_message;

import use_case.add_message.AddMessageInputBoundary;
import use_case.add_message.AddMessageInputData;

/**
 * Controller for the add message use case.
 */
public class AddMessageController {
    private final AddMessageInputBoundary addMessageUseCaseInteractor;

    public AddMessageController(AddMessageInputBoundary addMessageUseCaseInteractor) {
        this.addMessageUseCaseInteractor = addMessageUseCaseInteractor;
    }

    /**
     * Executes the add message use case.
     * @param text the text in the new message
     * @param currentUsername  the username of the sender of the message
     */
    public void execute(String text, String currentUsername) {
        final AddMessageInputData addMessageInputData = new AddMessageInputData(text, currentUsername);
        addMessageUseCaseInteractor.execute(addMessageInputData);
    }

    /**
     * Updates messages to be viewed.
     * @param discussion the current discussion
     */
    public void showMessages(String discussion) {
        addMessageUseCaseInteractor.showMessages(discussion);
    }
}
