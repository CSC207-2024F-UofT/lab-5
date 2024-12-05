package use_case.modeselection;

/**
 * Output Data for the Mode Selection Use Case.
 */
public class ModeSelectionOutputData {

    private final String selectedMode;

    /**
     * Constructs a ModeSelectionOutputData object with the selected mode and confirmation message.
     * @param selectedMode The mode that was selected by the user.
     */
    public ModeSelectionOutputData(String selectedMode) {
        this.selectedMode = selectedMode;
    }

    /**
     * Gets the selected mode.
     *
     * @return The mode selected by the user as a String.
     */
    public String getSelectedMode() {
        return selectedMode;
    }

}
