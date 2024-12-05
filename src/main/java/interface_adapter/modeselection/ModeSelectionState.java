package interface_adapter.modeselection;

/**
 * The state for the mode selection view model.
 */
public class ModeSelectionState {
    private String selectedMode = "";

    /**
     * Get the mode selected by the user.
     * @return selected mode
     */
    public String getSelectedMode() {
        return selectedMode;
    }

    /**
     * Set a mode.
     * @param selectedMode the mode that is set.
     */
    public void setSelectedMode(String selectedMode) {
        this.selectedMode = selectedMode;
    }

}
