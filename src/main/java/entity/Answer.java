package entity;

/**
 * Represents an answer option for a question in the question bank.
 * Each answer has text and a flag to indicate if it is the correct answer.
 */
public class Answer {
    private String answerText;
    private boolean isCorrect;

    /**
     * Constructs a new Answer with the specified text and correctness flag.
     *
     * @param answerText the text of the answer
     * @param isCorrect whether this answer is correct
     */
    public Answer(String answerText, boolean isCorrect) {
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    /**
     * Returns the text of the answer.
     *
     * @return the answer text
     */
    public String getAnswerText() {
        return answerText;
    }

    /**
     * Sets the text of the answer.
     *
     * @param answerText the text of the answer to set
     */
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    /**
     * Returns whether this answer is correct.
     *
     * @return true if this answer is correct, false otherwise
     */
    public boolean isCorrect() {
        return isCorrect;
    }

    /**
     * Sets the correctness of this answer.
     *
     * @param iscorrect true if this answer is correct, false otherwise
     */
    public void setCorrect(boolean iscorrect) {
        this.isCorrect = iscorrect;
    }
}
