package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a question in the question bank.
 * Each question has an ID, the question text, and a list of associated answers.
 */
public class Question {
    private int id;
    private String questionText;
    private List<Answer> answers = new ArrayList<>();

    /**
     * Constructs a new Question with the specified ID, question text, and answers.
     *
     * @param id the unique ID of the question
     * @param questionText the text of the question
     * @param answers the list of answers associated with the question
     */
    public Question(int id, String questionText, List<Answer> answers) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
    }

    /**
     * Returns the ID of the question.
     *
     * @return the question ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the question.
     *
     * @param id the question ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the text of the question.
     *
     * @return the question text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Sets the text of the question.
     *
     * @param questionText the text of the question
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Returns the list of answers associated with this question.
     *
     * @return a list of answers
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Adds an answer to the list of answers for this question.
     *
     * @param answer the answer to add
     */
    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

}
