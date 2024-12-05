package interface_adapter.show_notes;

import java.util.ArrayList;
import java.util.List;

/**
 * The State for the show topics View Model.
 */
public class ShowNotesState {
    private List<String> topics = new ArrayList<>();
    private String currentClub;
    private String currentNotes;
    private String errorMessage;

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(String currentClub) {
        this.currentClub = currentClub;
    }

    public String getCurrentNotes() {
        return currentNotes;
    }

    public void setCurrentNotes(String currentNotes) {
        this.currentNotes = currentNotes;
    }
}
