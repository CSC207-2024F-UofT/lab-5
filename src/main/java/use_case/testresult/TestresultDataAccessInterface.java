package use_case.testresult;

import java.util.ArrayList;

/**
 * DAO for the Test result Use Case.
 */
public interface TestresultDataAccessInterface {

    /**
     * Returns the number of the correct questions user got for test mode.
     * @return the number of the correct questions
     */
    int getCorrectQuestions();

    /**
     * Get a list of incorrect questions from a test.
     * @return a list of incorrect questions
     */
    ArrayList<String> getIncorrectQuestions();

    /**
     * Save number of correct questions.
     * @param correctQuestions number of correct questions
     */
    void saveCorrectQuestions(int correctQuestions);

    /**
     * Save the incorrect questions.
     * @param incorrectQuestions incorrect questions
     */
    void saveIncorrectQuestions(ArrayList<String> incorrectQuestions);
}
