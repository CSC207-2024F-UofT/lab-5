package use_case.testmodequestion;

import java.util.ArrayList;

/**
 * This save the option selected for future use.
 */
public interface TestModeQuestionDataAccessInterface {

    /**
     * Get correct questions.
     * @return correct questions
     */
    int getCorrectQuestions();

    /**
     * Set correct questions.
     * @param correctQuestions correct questions
     */
    void setCorrectQuestions(int correctQuestions);

    /**
     * Get incorrect questions.
     * @return incorrect questions
     */
    ArrayList<String> getIncorrectQuestions();

    /**
     * Set incorrect questions.
     * @param incorrectQuestions incorrect questions
     */
    void setIncorrectQuestions(ArrayList<String> incorrectQuestions);
}
