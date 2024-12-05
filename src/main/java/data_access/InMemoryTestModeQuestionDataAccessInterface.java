package data_access;

import java.util.ArrayList;

import use_case.testmodequestion.TestModeQuestionDataAccessInterface;

/**
 * In memory test mode questions Data Access Interface.
 */
public class InMemoryTestModeQuestionDataAccessInterface implements TestModeQuestionDataAccessInterface {

    private int correctQuestions;
    private ArrayList<String> incorrectQuestions;

    @Override
    public int getCorrectQuestions() {
        return this.correctQuestions;
    }

    @Override
    public void setCorrectQuestions(int correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    @Override
    public ArrayList<String> getIncorrectQuestions() {
        return incorrectQuestions;
    }

    @Override
    public void setIncorrectQuestions(ArrayList<String> incorrectQuestions) {
        this.incorrectQuestions = incorrectQuestions;
    }
}
