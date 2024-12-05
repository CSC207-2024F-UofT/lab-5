package use_case.add_message;

/**
 * Input data for the add_message usecase.
 */
public class AddMessageInputData {
    private final String text;
    private final String currentUsername;

    public AddMessageInputData(String text, String currentUsername) {
        this.text = text;
        this.currentUsername = currentUsername;
    }

    public String getText() {
        return text;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
