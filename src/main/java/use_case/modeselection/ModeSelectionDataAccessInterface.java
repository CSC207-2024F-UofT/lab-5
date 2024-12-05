package use_case.modeselection;

/**
 * This save the mode selected for future use.
 */
public interface ModeSelectionDataAccessInterface {

    /**
     * Save the specific mode selected.
     * @param mode The selected mode to be saved, represented as a String.
     */
    void saveSelectedMode(String mode);

    /**
     * Get the current selected mode.
     * @return current mode
     */
    String getSelectedMode();
}
