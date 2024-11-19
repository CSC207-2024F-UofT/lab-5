package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.survey1.SubmitController;
import interface_adapter.survey1.Survey1State;
import interface_adapter.survey1.Survey1ViewModel;

/**
 * The Survey View for when the user creates an account.
 * we need to call API to get the list of genres
 */
public class Survey1View extends JPanel implements PropertyChangeListener {

    private final String viewName = "survey step 1";
    private final Survey1ViewModel survey1ViewModel;
    private SubmitController submitController;

    private JButton horrorButton = new JButton("Horror");
    private JButton thrillerButton = new JButton("Thriller");
    private JButton dramaButton = new JButton("Drama");
    private JButton mysteryButton = new JButton("Mystery");
    private JButton familyButton = new JButton("Family");

    private JButton actionButton = new JButton("Action");
    private JButton romanceButton = new JButton("Romance");
    private JButton warButton = new JButton("War");
    private JButton crimeButton = new JButton("Crime");
    private JButton comedyButton = new JButton("Comedy");

    private JButton sciFiButton = new JButton("Sci-Fi");
    private JButton fantasyButton = new JButton("Fantasy");
    private JButton westernButton = new JButton("Western");
    private JButton musicButton = new JButton("Music");
    private JButton historyButton = new JButton("History");

    private JButton tvMovieButton = new JButton("TV-Movie");
    private JButton animationButton = new JButton("Animation");
    private JButton documentaryButton = new JButton("Documentary");
    private JButton adventureButton = new JButton("Adventure");

    private final JButton submit;

    public Survey1View(Survey1ViewModel survey1ViewModel) {
        this.survey1ViewModel = survey1ViewModel;
        this.survey1ViewModel.addPropertyChangeListener(this);

        final JLabel surveyQuestion = new JLabel("Select 3 of your favorite genres:");
        final JPanel surveyQuestionPanel = new JPanel();
        surveyQuestionPanel.add(surveyQuestion);

        final JPanel firstRowGenresButtons = new JPanel();
        horrorButton = new JButton("Horror");
        thrillerButton = new JButton("Thriller");
        dramaButton = new JButton("Drama");
        mysteryButton = new JButton("Mystery");
        familyButton = new JButton("Family");

        firstRowGenresButtons.add(horrorButton);
        firstRowGenresButtons.add(thrillerButton);
        firstRowGenresButtons.add(dramaButton);
        firstRowGenresButtons.add(mysteryButton);
        firstRowGenresButtons.add(familyButton);

        final JPanel secondRowGenresButtons = new JPanel();
        actionButton = new JButton("Action");
        romanceButton = new JButton("Romance");
        warButton = new JButton("War");
        crimeButton = new JButton("Crime");
        comedyButton = new JButton("Comedy");
        secondRowGenresButtons.add(actionButton);
        secondRowGenresButtons.add(romanceButton);
        secondRowGenresButtons.add(warButton);
        secondRowGenresButtons.add(crimeButton);
        secondRowGenresButtons.add(comedyButton);

        final JPanel thirdRowGenresButtons = new JPanel();
        sciFiButton = new JButton("Sci-Fi");
        fantasyButton = new JButton("Fantasy");
        westernButton = new JButton("Western");
        musicButton = new JButton("Music");
        historyButton = new JButton("History");
        thirdRowGenresButtons.add(sciFiButton);
        thirdRowGenresButtons.add(fantasyButton);
        thirdRowGenresButtons.add(westernButton);
        thirdRowGenresButtons.add(musicButton);
        thirdRowGenresButtons.add(historyButton);

        final JPanel fourthRowGenresButtons = new JPanel();
        tvMovieButton = new JButton("TV-Movie");
        animationButton = new JButton("Animation");
        documentaryButton = new JButton("Documentary");
        adventureButton = new JButton("Adventure");
        fourthRowGenresButtons.add(tvMovieButton);
        fourthRowGenresButtons.add(animationButton);
        fourthRowGenresButtons.add(documentaryButton);
        fourthRowGenresButtons.add(adventureButton);

        submit = new JButton("Submit");
        // Disable submit initially
        submit.setEnabled(false);
        final JPanel submitPanel = new JPanel();
        submitPanel.add(submit);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        submit.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                evt -> {
//                    if (evt.getSource().equals(submit)) {
//                        // 1. get the state out of the survey1ViewModel.
//                        // 2. Execute the submit Controller.
//                        final String currentState = survey1ViewModel.getState();
//                        this.submitController.execute(currentState);
//                    }
//                });
        this.add(surveyQuestionPanel);
        this.add(firstRowGenresButtons);
        this.add(secondRowGenresButtons);
        this.add(thirdRowGenresButtons);
        this.add(fourthRowGenresButtons);
        this.add(submitPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final Survey1State state = (Survey1State) evt.getNewValue();
        }
        else if (evt.getPropertyName().equals("password")) {
            final Survey1State state = (Survey1State) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for ");
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setSubmitController(SubmitController submitController) {
        this.submitController = submitController;
    }

}
