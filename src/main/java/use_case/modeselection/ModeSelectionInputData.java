package use_case.modeselection;

/**
 * The Input Data for the Mode Selection Use Case.
 */
public class ModeSelectionInputData {
    private final String selectedMode;

    public ModeSelectionInputData(String selectedMode) {
        this.selectedMode = selectedMode;
    }

    public String getSelectedMode() {
        return selectedMode;
    }
}
