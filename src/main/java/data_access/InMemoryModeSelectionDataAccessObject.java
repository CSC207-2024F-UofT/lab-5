package data_access;

import use_case.modeselection.ModeSelectionDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing mode selection data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryModeSelectionDataAccessObject implements ModeSelectionDataAccessInterface {
    private String selectedMode;

    @Override
    public void saveSelectedMode(String mode) {
        this.selectedMode = mode;
    }

    @Override
    public String getSelectedMode() {
        return this.selectedMode;
    }

}
