package interface_adapter.add_message;

import use_case.add_message.AddMessageOutputBoundary;
import use_case.add_message.AddMessageOutputData;

/**
 * The Presenter for the add message use case.
 */
public class AddMessagePresenter implements AddMessageOutputBoundary {

    private final AddMessageViewModel addMessageViewModel;

    public AddMessagePresenter(AddMessageViewModel addMessageViewModel) {
        this.addMessageViewModel = addMessageViewModel;
    }

    @Override
    public void prepareShowMessageView(AddMessageOutputData outputData) {
        final AddMessageState addMessageState = addMessageViewModel.getState();
        addMessageState.setMessagesList(outputData.getMessages());
        addMessageViewModel.setState(addMessageState);
        addMessageViewModel.firePropertyChanged();
    }
}
