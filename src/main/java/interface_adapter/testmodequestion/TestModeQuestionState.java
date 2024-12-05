package interface_adapter.testmodequestion;

import java.util.ArrayList;

/**
 * The state for the test mode question view model.
 */
public class TestModeQuestionState {
    private String selectedOption = "";
    private int correctQuestions;
    private ArrayList<String> incorrectQuestions;

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

    /**
     * Get correct questions.
     * @return correct questions.
     */
    public int getCorrectQuestions() {
        return correctQuestions;
    }

    /**
     * Set correct questions.
     * @param correctQuestions correct question
     */
    public void setCorrectQuestions(int correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    /**
     * Get incorrect Questions.
     * @return incorrect questions
     */
    public ArrayList<String> getIncorrectQuestions() {
        return incorrectQuestions;
    }

    /**
     * Set incorrect questions.
     * @param incorrectQuestions incorrect questions
     */
    public void setIncorrectQuestions(ArrayList<String> incorrectQuestions) {
        this.incorrectQuestions = incorrectQuestions;
    }
}
