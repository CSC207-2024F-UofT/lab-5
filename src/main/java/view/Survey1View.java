package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import data_access.GenreMap;
import interface_adapter.survey1.SubmitController;
import interface_adapter.survey1.Survey1State;
import interface_adapter.survey1.Survey1ViewModel;

/**
 * The Survey View for when the user creates an account.
 * we need to call API to get the list of genres
 */
public class Survey1View extends JPanel implements PropertyChangeListener {
    private final GenreMap genreMap = new GenreMap();

    private final String viewName = "survey step 1/2";
    private final Survey1ViewModel survey1ViewModel;

    private final List<String> selectedGenres = new ArrayList<>();
    private SubmitController submitController;

    private final JButton submit;
    private final JLabel errorLabel;

    public Survey1View(Survey1ViewModel survey1ViewModel) throws IOException {
        this.survey1ViewModel = survey1ViewModel;
        this.survey1ViewModel.addPropertyChangeListener(this);

        final JLabel surveyQuestion = new JLabel("Select 3 of your favorite genres:");
        surveyQuestion.setFont(new Font("Ariel", Font.PLAIN, 15));

        final JPanel surveyQuestionPanel = new JPanel();
        surveyQuestionPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        surveyQuestionPanel.add(surveyQuestion);

        final JPanel genreButtonsPanel = new JPanel();
        genreButtonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        genreButtonsPanel.setPreferredSize(new Dimension(450, 200));
        for (String genre : genreMap.keySet()) {
            final JButton button = new JButton(genre);

            // styling
            button.setPreferredSize(new Dimension(110, 40));
            button.setBorder(new EtchedBorder(EtchedBorder.RAISED));

            addGenreButton(button, genre);
            genreButtonsPanel.add(button);
        }

        errorLabel = new JLabel("You need to select 3 more genres."); // Initial message
        errorLabel.setFont(new Font("Ariel", Font.PLAIN, 13));
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        submit = new JButton("Submit");
        submit.setPreferredSize(new Dimension(100, 40));

        // Disable submit initially
        submit.setEnabled(false);
        final JPanel submitPanel = new JPanel();
        submitPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitPanel.add(submit);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(450, 500));

        submit.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(submit)) {
                        // 1. get the state out of the survey1ViewModel.
                        // 2. Execute the submit Controller.
                        final String uname = survey1ViewModel.getState().getUsername();
                        this.submitController.execute(selectedGenres, uname);
                    }
                }
        );

        this.add(surveyQuestionPanel);
        this.add(genreButtonsPanel);
        this.add(errorLabel);
        this.add(submitPanel);
    }

    // Method to add action listeners to genre buttons
    private void addGenreButton(JButton button, String genre) {
        final Color selectedGreen = new Color(14, 220, 14);
        button.addActionListener(e -> {
            if (selectedGenres.contains(genre)) {
                // If the genre is already selected, deselect it
                selectedGenres.remove(genre);
                // Reset button color to default
                button.setBackground(null);

            }
            else if (selectedGenres.size() < 3) {
                // If less than 3 genres are selected, add the new genre
                selectedGenres.add(genre);
                button.setBackground(selectedGreen); // Highlight the button as selected
            }

            // Enable the submit button only if exactly 3 genres are selected
            submit.setEnabled(selectedGenres.size() == 3);

            // Update error message
            final int remaining = 3 - selectedGenres.size();
            if (remaining > 0) {
                errorLabel.setText(
                        "You need to select " + remaining + " more genre" + (remaining > 1 ? "s" : "") + ".");
            }
            else {
                errorLabel.setText(""); // Clear error message when exactly 3 genres are selected
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final Survey1State state = (Survey1State) evt.getNewValue();
        if (state.getSurvey1Error() != null) {
            JOptionPane.showMessageDialog(this, state.getSurvey1Error());
        }
        else if (evt.getPropertyName().equals("state")) {
            // Enable the submit button only if exactly 3 genres are selected
            submit.setEnabled(selectedGenres.size() == 3);
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSubmitController(SubmitController submitController) {
        this.submitController = submitController;
    }
}
