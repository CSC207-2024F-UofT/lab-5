package interface_adapter.testresult;

import java.util.ArrayList;

import interface_adapter.ViewModel;

/**
 * The ViewModel for test result view.
 */
public class TestresultViewModel extends ViewModel<TestresultState> {
    public static final String TITLE_LABEL = "Test Result";

    public TestresultViewModel() {
        super("test result");
        setState(new TestresultState());
    }

    /**
     * A getter for number of correct questions.
     * @return number of correct questions
     */
    public int getCorrectQuestions() {
        return getState().getCorrectQuestions();
    }

    /**
     * A setter for correct questions.
     * @param correctQuestions number of correct questions
     */
    public void setCorrectQuestions(int correctQuestions) {
        getState().setCorrectQuestions(correctQuestions);
    }

    /**
     * A getter for incorrect questions.
     * @return incorrect questions
     */
    public ArrayList<String> getIncorrectQuestions() {
        return getState().getIncorrectQuestions();
    }

    /**
     * A setter for incorrect questions.
     * @param incorrectQuestions incorrect questions
     */
    public void setIncorrectQuestions(ArrayList<String> incorrectQuestions) {
        getState().setIncorrectQuestions(incorrectQuestions);
    }

}
