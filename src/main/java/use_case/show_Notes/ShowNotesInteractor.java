package use_case.show_Notes;

import java.util.List;

/**
 * The show Notes interactor.
 */
public class ShowNotesInteractor implements ShowNotesInputBoundary {

    private final ShowNotesOutputBoundary showNotesOutputBoundary;
    private final ShowNotesDataAccessInterface showNotesDataAccessInterface;

    public ShowNotesInteractor(ShowNotesDataAccessInterface showTopicsDataAccessInterface,
                               ShowNotesOutputBoundary showTopicsOutputBoundary) {

        this.showNotesOutputBoundary = showTopicsOutputBoundary;
        this.showNotesDataAccessInterface = showTopicsDataAccessInterface;
    }

    @Override
    public void execute() {
        final List<String> topics = showNotesDataAccessInterface.getNotesTopics();

        if (topics.isEmpty()) {
            showNotesOutputBoundary.prepareFailView("There are no Notes here, create a new Note");
        }

        else {
            final ShowNotesOutputData outputData = new ShowNotesOutputData(topics);
            showNotesOutputBoundary.prepareSuccessView(outputData);
        }

    }

    @Override
    public void switchToAddMessageView(String Note) {
        showNotesDataAccessInterface.setCurrentNote(Note);
        showNotesOutputBoundary.switchToAddMessageView(Note);
    }
}
