package interface_adapter.testresult;

import java.util.ArrayList;

/**
 * State of Test Result use case.
 */
public class TestresultState {
    private int correctQuestions;
    private ArrayList<String> incorrectQuestions = new ArrayList<>();
    private String testResultError;

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

    /**
     * Set test result error.
     * @param testResultError test result error
     */
    public void setTestResultError(String testResultError) {
        this.testResultError = testResultError;
    }

}
