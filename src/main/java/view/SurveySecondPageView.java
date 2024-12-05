package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.survey_second_page.SurveySecondPageController;
import interface_adapter.survey_second_page.SurveySecondPageState;
import interface_adapter.survey_second_page.SurveySecondPageViewModel;

/**
 * The Second Survey View for when the user is taking the recommendation survey.
 */
public class SurveySecondPageView extends JPanel implements ActionListener, PropertyChangeListener {

    private String viewName = "";
    private final SurveySecondPageViewModel surveySecondPageViewModel;

    private final JTextField firstMovie = new JTextField(30);
    private final JTextField secondMovie = new JTextField(30);
    private final JTextField thirdMovie = new JTextField(30);

    private final JButton enter1;
    private final JButton enter2;
    private final JButton enter3;

    private final JButton submit;
    private final JButton skip;

    private final JLabel searchInputErrorField = new JLabel();

    private SurveySecondPageController surveySecondPageController;

    public SurveySecondPageView(SurveySecondPageViewModel surveySecondPageViewModel) {
        this.surveySecondPageViewModel = surveySecondPageViewModel;
        this.surveySecondPageViewModel.addPropertyChangeListener(this);
        this.viewName = surveySecondPageViewModel.getViewName();

        this.enter1 = new JButton(SurveySecondPageViewModel.ENTER_LABEL);
        enter1.setBackground(new Color(173, 216, 230));
        enter1.setOpaque(true);
        enter1.setBorderPainted(false);
        enter1.setForeground(Color.BLACK);
        this.enter2 = new JButton(SurveySecondPageViewModel.ENTER_LABEL);
        enter2.setBackground(new Color(173, 216, 230));
        enter2.setOpaque(true);
        enter2.setBorderPainted(false);
        enter2.setForeground(Color.BLACK);
        this.enter3 = new JButton(SurveySecondPageViewModel.ENTER_LABEL);
        enter3.setBackground(new Color(173, 216, 230));
        enter3.setOpaque(true);
        enter3.setBorderPainted(false);
        enter3.setForeground(Color.BLACK);

        this.submit = new JButton(SurveySecondPageViewModel.SUBMIT_LABEL);
        this.skip = new JButton(SurveySecondPageViewModel.SKIP_LABEL);

        final JLabel title = new JLabel("Enter 3 of your favorite movies");
        title.setFont(new Font("Ariel", Font.PLAIN, 15));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        submit.setEnabled(false);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(submit);
        buttonPanel.add(skip);

        final JPanel searchPanel1 = new JPanel();
        searchPanel1.add(firstMovie);
        searchPanel1.add(enter1);

        final JPanel searchPanel2 = new JPanel();
        searchPanel2.add(secondMovie);
        searchPanel2.add(enter2);

        final JPanel searchPanel3 = new JPanel();
        searchPanel3.add(thirdMovie);
        searchPanel3.add(enter3);

        enter1.addActionListener(evt -> handleFirstMovieEntry());
        enter2.addActionListener(evt -> handleSecondMovieEntry());
        enter3.addActionListener(evt -> handleThirdMovieEntry());

        submit.addActionListener(evt -> handleSubmitButtonClick());

        skip.addActionListener(evt -> handleSkipButtonClick());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(searchPanel1);
        this.add(searchPanel2);
        this.add(searchPanel3);
        this.add(searchInputErrorField);
        this.add(buttonPanel);
    }

    private void handleFirstMovieEntry() {
        final SurveySecondPageState currentState = surveySecondPageViewModel.getState();
        currentState.setFirstMovie(firstMovie.getText());
        surveySecondPageViewModel.setState(currentState);
        validateSubmitButton();
    }

    private void handleSecondMovieEntry() {
        final SurveySecondPageState currentState = surveySecondPageViewModel.getState();
        currentState.setSecondMovie(secondMovie.getText());
        surveySecondPageViewModel.setState(currentState);
        validateSubmitButton();
    }

    private void handleThirdMovieEntry() {
        final SurveySecondPageState currentState = surveySecondPageViewModel.getState();
        currentState.setThirdMovie(thirdMovie.getText());
        surveySecondPageViewModel.setState(currentState);
        validateSubmitButton();
    }

    private void handleSubmitButtonClick() {
        // TODO: save movies
        surveySecondPageController.switchToHomeView();
    }

    private void handleSkipButtonClick() {
        surveySecondPageController.switchToHomeView();
    }

    private void validateSubmitButton() {
        // Enable submit if all movies have been entered
        submit.setEnabled(surveySecondPageViewModel.getState().canSubmit());
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(
                this, "Button not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SurveySecondPageState state = (SurveySecondPageState) evt.getNewValue();
        if (state.getMovieEntryError() != null) {
            JOptionPane.showMessageDialog(this, state.getMovieEntryError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSurveySecondPageController(SurveySecondPageController controller) {
        this.surveySecondPageController = controller;
    }
}
