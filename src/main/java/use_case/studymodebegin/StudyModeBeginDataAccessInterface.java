package use_case.studymodebegin;

/**
 * DAO for the Study Mode Begin Use Case.
 */
public interface StudyModeBeginDataAccessInterface {
    /**
     * Returns the module that the user chose.
     * @return the module that the user chose; null indicates that the user didn't choose a module.
     */
    String getModule();

    /**
     * Sets the module indicating the new module that user chose.
     * @param module the new current module; null to indicate that no module is currently selected.
     */
    void setModule(String module);
}
