package interface_adapter.add_message;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

/**
 * The State for the add message view model.
 */
public class AddMessageState {
    private String text = "";
    private List<AbstractMap.SimpleEntry<String, String>> messagesList = new ArrayList<>();
    private String currentNote;
    private String currentUsername;

    public void setText(String text) {
        this.text = text;
    }

    public void setMessagesList(List<AbstractMap.SimpleEntry<String, String>> messagesList) {
        this.messagesList = messagesList;
    }

    public void setCurrentNote(String currentNote) {
        this.currentNote = currentNote;
    }

    public String getText() {
        return text;
    }

    public List<AbstractMap.SimpleEntry<String, String>> getMessagesList() {
        return messagesList;
    }

    public String getCurrentNote() {
        return currentNote;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }
}
