package use_case.testresult;

import java.util.ArrayList;

/**
 * The input data for test result use case.
 */
public class TestresultInputData {
    private final int correctQuestions;
    private final ArrayList<String> incorrectQuestions;

    public TestresultInputData(int correctQuestions, ArrayList<String> incorrectQuestions) {
        this.correctQuestions = correctQuestions;
        this.incorrectQuestions = incorrectQuestions;
    }

    public int getCorrectQuestions() {
        return correctQuestions;
    }

    public ArrayList<String> getIncorrectQuestions() {
        return incorrectQuestions;
    }
}
