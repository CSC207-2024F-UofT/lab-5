package interface_adapter.studymodequestion;

/**
 * The state for the study mode question view model.
 */
public class StudyModeQuestionState {
    private String module = "";
    private String selectedOption = "";

    /**
     * Get the option selected by the user.
     * @return selected option
     */
    public String getSelectedOption() {
        return selectedOption;
    }

    /**
     * Set a option.
     * @param selectedOption the option that is set.
     */
    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

}
