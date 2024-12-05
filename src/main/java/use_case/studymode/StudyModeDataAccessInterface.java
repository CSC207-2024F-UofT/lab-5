package use_case.studymode;

/**
 * DAO for the Study Mode Use Case.
 */
public interface StudyModeDataAccessInterface {
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
