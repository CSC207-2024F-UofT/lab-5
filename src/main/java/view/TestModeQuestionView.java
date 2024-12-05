package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import data_access.DatabaseRetriever;
import entity.Answer;
import entity.Question;
import interface_adapter.logout.LogoutController;
import interface_adapter.testmodequestion.TestModeQuestionController;
import interface_adapter.testmodequestion.TestModeQuestionState;
import interface_adapter.testmodequestion.TestModeQuestionViewModel;

/**
 * The View of Test Mode Question.
 */
public class TestModeQuestionView extends JPanel implements ActionListener {
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int TEN = 10;
    private static final int THRITY = 30;
    private static final int EIGHTY = 80;
    private static final int THREEHUNDRED = 300;
    private static final int FOURHUNDRED = 400;

    private final String viewName = "test mode question";

    private LogoutController logoutController;
    private final TestModeQuestionViewModel testModeQuestionViewModel;
    private TestModeQuestionController testModeQuestionController;

    private final JLabel testmodequestion;
    private final JButton option1;
    private final JButton option2;
    private final JButton option3;
    private final JButton option4;
    private JButton nextButton;

    private LinkedList<Question> questions;
    private Question currentQuestion;
    private ArrayList<String> wrongquestions = new ArrayList<>();
    private int correctquestions;

    public TestModeQuestionView(TestModeQuestionViewModel testModeQuestionViewModel) throws SQLException {
        this.testModeQuestionViewModel = testModeQuestionViewModel;

        this.setBackground(Color.decode("#9BA8AB"));

        // uncomment for full version of test mode question
        // this.questions = DatabaseRetriever.getQuestionsRandom();
        this.questions = DatabaseRetriever.getQuestionsInRange(0, THREE);
        this.wrongquestions = new ArrayList<>();

        final JLabel title = getjLabel();

        testmodequestion = new JLabel("Question needed to be added");
        testmodequestion.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        //        buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        option1 = new JButton("Option 1");
        buttons.add(option1);
        buttons.add(Box.createVerticalStrut(TEN));

        option2 = new JButton("Option 2");
        buttons.add(option2);
        buttons.add(Box.createVerticalStrut(TEN));

        option3 = new JButton("Option 3");
        buttons.add(option3);
        buttons.add(Box.createVerticalStrut(TEN));

        option4 = new JButton("Option 4");
        buttons.add(option4);
        buttons.add(Box.createVerticalStrut(TEN));

        nextButton = getNextButton(buttons);

        loadNextQuestion();

        // Take user to 'begin view' page based on their selection
        option1.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        optionsaction(0, option1);
                    }
                }
        );

        option2.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        optionsaction(1, option2);
                    }
                }
        );

        option3.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        optionsaction(2, option3);
                    }
                }
        );

        option4.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        optionsaction(THREE, option4);
                    }
                }
        );

        nextButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        // Disable again until a new option is selected
                        nextButton.setEnabled(false);
                        loadNextQuestion();
                    }
                }
        );

        addTo(title, buttons);

    }

    private void addTo(JLabel title, JPanel buttons) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(testmodequestion);
        this.add(buttons);
    }

    @NotNull
    private JButton getNextButton(JPanel buttons) {
        this.nextButton = new JButton("Next");
        // disable nextButton at first
        nextButton.setEnabled(false);
        nextButton.setBounds(THREEHUNDRED, FOURHUNDRED, EIGHTY, THRITY);
        buttons.add(nextButton);
        buttons.setBackground(Color.decode("#9BA8AB"));
        return nextButton;
    }

    @NotNull
    private static JLabel getjLabel() {
        final JLabel title = new JLabel("Test Mode Question");
        final int fontsize = 25;
        title.setFont(new Font("Times New Roman", Font.ITALIC, fontsize));
        title.setForeground(Color.decode("#4A5C6A"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }

    private void optionsaction(int id, JButton options) {
        if (this.currentQuestion.getAnswers().get(id).isCorrect()) {
            options.setBackground(Color.GREEN);
            options.revalidate();
            options.setOpaque(true);
            options.repaint();
            this.correctquestions++;

        }
        else {
            options.setBackground(Color.RED);
            options.revalidate();
            options.setOpaque(true);
            options.repaint();
            if (!(this.wrongquestions.contains(this.currentQuestion))) {
                this.wrongquestions.add(this.currentQuestion.getQuestionText());
            }
        }
        // Enable "Next" button after an option is selected
        this.nextButton.setEnabled(true);
    }

    private void loadNextQuestion() {
        if (!questions.isEmpty()) {
            currentQuestion = questions.poll();
            testmodequestion.setText("<html><p style='width:350px;'>" + currentQuestion.getQuestionText()
                    + "</p></html>");

            final List<Answer> answers = currentQuestion.getAnswers();
            final JButton[] options = {option1, option2, option3, option4};

            for (int i = 0; i < FOUR; i++) {
                if (i < answers.size()) {
                    options[i].setText("<html><p style='width:300px;'>" + answers.get(i).getAnswerText() 
                                       + "</p></html>");
                    options[i].setEnabled(true);
                    // Reset button color
                    options[i].setBackground(null);
                }
                else {
                    options[i].setText("N/A");
                    options[i].setEnabled(false);
                }
            }
        }
        else {
            // End session if no more questions
            // link to test result
            final TestModeQuestionState testModeQuestionState = testModeQuestionViewModel.getState();
            testModeQuestionState.setCorrectQuestions(correctquestions);
            testModeQuestionState.setIncorrectQuestions(wrongquestions);
            System.out.println(wrongquestions);
            testModeQuestionViewModel.setState(testModeQuestionState);
            testModeQuestionViewModel.firePropertyChanged();
            testModeQuestionController.execute(testModeQuestionState.getCorrectQuestions(),
                    testModeQuestionState.getIncorrectQuestions());
            testModeQuestionController.switchToTestResultView();

        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setTestModeQuestionController(TestModeQuestionController testModeQuestionController) {
        this.testModeQuestionController = testModeQuestionController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

}
