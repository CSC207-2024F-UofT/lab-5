package use_case.add_message;

/**
 * The add message interactor.
 */
public class AddMessageInteractor implements AddMessageInputBoundary {

    private final AddMessageDataAccessInterface addMessageDataAccessInterface;
    private final AddMessageOutputBoundary addMessageOutputBoundary;

    public AddMessageInteractor(AddMessageDataAccessInterface addMessageDataAccessInterface,
                                AddMessageOutputBoundary addMessageOutputBoundary) {
        this.addMessageDataAccessInterface = addMessageDataAccessInterface;
        this.addMessageOutputBoundary = addMessageOutputBoundary;
    }

    @Override
    public void execute(AddMessageInputData addMessageInputData) {

        final String text = addMessageInputData.getText();
        final String currentUsername = addMessageInputData.getCurrentUsername();

        addMessageDataAccessInterface.saveMessage(text, currentUsername);

        final AddMessageOutputData outputData =
                new AddMessageOutputData(addMessageDataAccessInterface.getMessages(), addMessageDataAccessInterface
                        .getCurrentNote());

        addMessageOutputBoundary.prepareShowMessageView(outputData);
    }

    @Override
    public void showMessages(String note) {
        final AddMessageOutputData outputData =
                new AddMessageOutputData(addMessageDataAccessInterface.getMessages(), note);
        addMessageOutputBoundary.prepareShowMessageView(outputData);
    }

}
