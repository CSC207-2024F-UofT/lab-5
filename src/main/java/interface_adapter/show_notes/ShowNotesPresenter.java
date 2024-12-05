package interface_adapter.show_notes;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_message.AddMessageState;
import interface_adapter.add_message.AddMessageViewModel;
import use_case.show_Notes.ShowNotesOutputBoundary;
import use_case.show_Notes.ShowNotesOutputData;

/**
 * The presenter for the show topics usecase.
 */
public class ShowNotesPresenter implements ShowNotesOutputBoundary {

    private final ShowNotesViewModel showNotesViewModel;
    private final AddMessageViewModel addMessageViewModel;
    private final ViewManagerModel viewManagerModel;

    public ShowNotesPresenter(ShowNotesViewModel showNotesViewModel,
                              ViewManagerModel viewManagerModel, AddMessageViewModel addMessageViewModel) {
        this.showNotesViewModel = showNotesViewModel;
        this.viewManagerModel = viewManagerModel;
        this.addMessageViewModel = addMessageViewModel;
    }

    @Override
    public void prepareSuccessView(ShowNotesOutputData outputData) {
        final ShowNotesState state = showNotesViewModel.getState();
        state.setTopics(outputData.getTopics());
        showNotesViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ShowNotesState state = showNotesViewModel.getState();
        state.setErrorMessage(errorMessage);
        showNotesViewModel.firePropertyChanged("error");
    }

    @Override
    public void switchToAddMessageView(String discussion) {
        final AddMessageState state = addMessageViewModel.getState();
        state.setCurrentNote(discussion);
        addMessageViewModel.firePropertyChanged();

        viewManagerModel.setState(addMessageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

}
