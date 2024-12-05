package use_case.testresult;

import java.util.ArrayList;

/**
 * Outputdata for Test Result use case.
 */
public class TestresultOutputData {
    private final int correctQuestions;
    private final ArrayList<String> incorrectQuestions;

    public TestresultOutputData(int correctQuestions, ArrayList<String> incorrectQuestions) {
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
