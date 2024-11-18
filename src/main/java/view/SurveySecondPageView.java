package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
// import javax.swing.event.DocumentEvent;
// import javax.swing.event.DocumentListener;

import interface_adapter.survey_second_page.SurveySecondPageController;
import interface_adapter.survey_second_page.SurveySecondPageState;
import interface_adapter.survey_second_page.SurveySecondPageViewModel;

/**
 * The Second Survey View for when the user is taking the recommendation survey.
 */
public class SurveySecondPageView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "survey page 2/2";
    private final SurveySecondPageViewModel surveySecondPageViewModel;

    private SurveySecondPageController surveySecondPageController;

    private final JTextField firstMovie = new JTextField(15);
    private final JTextField secondMovie = new JTextField(15);
    private final JTextField thirdMovie = new JTextField(15);

    private final JButton enter1;
    private final JButton enter2;
    private final JButton enter3;

    private final JButton submit;
    private final JButton skip;

    private final JLabel searchInputErrorField = new JLabel();

    public SurveySecondPageView(SurveySecondPageViewModel surveySecondPageViewModel) {
        this.surveySecondPageViewModel = surveySecondPageViewModel;
        this.surveySecondPageViewModel.addPropertyChangeListener(this);

        this.enter1 = new JButton(surveySecondPageViewModel.ENTER_LABEL);
        this.enter2 = new JButton(surveySecondPageViewModel.ENTER_LABEL);
        this.enter3 = new JButton(surveySecondPageViewModel.ENTER_LABEL);

        this.submit = new JButton(surveySecondPageViewModel.SUBMIT_LABEL);
        this.skip = new JButton(surveySecondPageViewModel.SKIP_LABEL);

        final JLabel title = new JLabel("Enter 3 of your favorite movies");
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
        JOptionPane.showMessageDialog(this, "Button not implemented yet.");
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
