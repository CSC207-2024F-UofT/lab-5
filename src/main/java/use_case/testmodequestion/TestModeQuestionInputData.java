package use_case.testmodequestion;

import java.util.ArrayList;

/**
 * InputData for the TestModeQuestion Use Case.
 */
public class TestModeQuestionInputData {
    private final int correctanswer;
    private final ArrayList<String> wrongquestions;

    public TestModeQuestionInputData(int correctanswer, ArrayList<String> wrongquestions) {
        this.correctanswer = correctanswer;
        this.wrongquestions = wrongquestions;
    }

    public int getCorrectAnswer() {
        return correctanswer;
    }

    public ArrayList<String> getWrongquestions() {
        return wrongquestions;
    }
}
