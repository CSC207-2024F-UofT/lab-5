package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;

    private final JLabel username;

    private final JButton logOut;
    private final JButton translate;
    private final JButton history;
    private final JButton changepassword;

    private final JTextField languageInputField = new JTextField(15);
    private final JTextField languageOutputField = new JTextField(15);

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Translator");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel languageInput = new LabelTextPanel(
                new JLabel(""), languageInputField);

        final LabelTextPanel languageOutput = new LabelTextPanel(
                new JLabel(""), languageOutputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JLabel languageInfo = new JLabel("Language");
        final JLabel translatedLanguageInfo = new JLabel("             Language");

        final JLabel sourceLanguageLabel = new JLabel("Source Language: ");
        final JLabel translatedLanguageLabel = new JLabel("Translated Language: ");

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        translate = new JButton("Translate");
        buttons.add(translate);

        history = new JButton("History");
        buttons.add(history);

        changepassword = new JButton("Change Password");
        buttons.add(changepassword);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        firstRow.add(sourceLanguageLabel);
        firstRow.add(buttons.getComponent(1));
        firstRow.add(translatedLanguageLabel);

        final JPanel secondRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        secondRow.add(languageInput);
        secondRow.add(languageOutput);

        final JPanel thirdRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        thirdRow.add(languageInfo);
        thirdRow.add(translatedLanguageInfo);

        final JPanel fourthRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fourthRow.add(history);
        fourthRow.add(bookmark);
        fourthRow.add(feedback);
        fourthRow.add(changepassword);

        languageInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(languageInputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        languageOutputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(languageOutputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        translate.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(translate)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        this.TranslatorController.execute(
                                currentState.getUsername()
                        );
                    }
                }
        );

        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            // 1. get the state out of the loggedInViewModel. It contains the username.
                            // 2. Execute the logout Controller.
                            final LoggedInState currentState = loggedInViewModel.getState();

                            logoutController.execute(
                                    currentState.getUsername()
                            );
                        }
                    }
                }
        );

        changepassword.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(changepassword)) {
                            final LoggedInState currentState = loggedInViewModel.getState();

                            ChangePasswordController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.add(firstRow);

        this.add(seconedRow);

        this.add(thiredRow);

        this.add(buttons.getComponent(0));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }
}
