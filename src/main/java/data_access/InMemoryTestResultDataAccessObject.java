package data_access;

import java.util.ArrayList;

import use_case.testresult.TestresultDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing test result data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryTestResultDataAccessObject implements TestresultDataAccessInterface {
    private int correctQuestions;
    private ArrayList<String> incorrectQuestions = new ArrayList<>();

    @Override
    public int getCorrectQuestions() {
        return correctQuestions;
    }

    @Override
    public ArrayList<String> getIncorrectQuestions() {
        return incorrectQuestions;
    }

    @Override
    public void saveCorrectQuestions(int numCorrectQuestions) {
        this.correctQuestions = numCorrectQuestions;
    }

    @Override
    public void saveIncorrectQuestions(ArrayList<String> incorrectQuestionsList) {
        this.incorrectQuestions = incorrectQuestionsList;
    }
}
