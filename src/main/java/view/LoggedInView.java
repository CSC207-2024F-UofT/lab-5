package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
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
import interface_adapter.modeselection.ModeSelectionController;
import interface_adapter.modeselection.ModeSelectionViewModel;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private ModeSelectionController modeSelectionController;
    private final JLabel username;

    private final JTextField passwordInputField = new JTextField(15);
    private final String backGroundc = "#11212D";

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.setBackground(Color.decode(backGroundc));

        final JLabel changeForPassword = new JLabel("If you wish to change your password, please "
                + "enter the new Password below");
        changeForPassword.setForeground(Color.decode("#92A1C2"));
        changeForPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("<html><span style='color: #5483B3'>New Password </span></html>"), passwordInputField);
        passwordInfo.setBackground(Color.decode(backGroundc));

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setForeground(Color.decode("#4A5C6A"));
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel modeSelection = new JLabel("<html><div style='text-align: center; font-family: "
                + "\"Times New Roman\"; margin: 10px auto; color: #7DA0CA; font-size: 25'>"
                + "Choose a Mode: ");
        modeSelection.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = createTitle();
        this.add(title);
        this.add(modeSelection);
        final JPanel buttonsPanel = createModeButtonsPanel();
        this.add(buttonsPanel);

        this.add(changeForPassword);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        final JPanel buttonWrapper = createButtonsWrapper();
        this.add(buttonWrapper);

        this.add(usernameInfo);
        this.add(username);
    }

    private JLabel createTitle() {
        final int titleFontSize = 25;
        final JLabel title = new JLabel("Mode Selection");
        title.setFont(new Font("Times New Roman", Font.ITALIC, titleFontSize));
        title.setForeground(Color.decode("#4A5C6A"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }

    private JPanel createModeButtonsPanel() {

        final JPanel buttons = new JPanel();
        buttons.setBackground(Color.decode(backGroundc));
        final int buttonsRows = 1;
        final int buttonsCols = 2;
        final int buttonsHgap = 10;
        final int buttonsVgap = 10;
        buttons.setLayout(new GridLayout(buttonsCols, buttonsRows, buttonsHgap, buttonsVgap));

        final String foreColor = "#2e365a";
        final String backColor = "#7DA0CA";
        final JButton study = new JButton(ModeSelectionViewModel.STUDY_MODE_BUTTON_LABEL);
        final JButton test = new JButton(ModeSelectionViewModel.TEST_MODE_BUTTON_LABEL);

        study.setForeground(Color.decode(foreColor));
        test.setForeground(Color.decode(foreColor));

        study.setBackground(Color.decode(backColor));
        test.setBackground(Color.decode(backColor));

        buttons.add(study);
        buttons.add(test);

        study.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        modeSelectionController.switchToStudyModeView();
                    }
                }
        );

        test.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        modeSelectionController.switchToTestModeView();
                    }
                }
        );
        return buttons;
    }

    private JPanel createButtonsWrapper() {

        final int buttonsRows = 1;
        final int buttonsCols = 2;
        final int buttonsHgap = 10;
        final int buttonsVgap = 10;

        final JButton logOut = new JButton("Log Out");
        logOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JButton changePassword = new JButton("Change Password");
        changePassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        logOut.setBackground(Color.decode("#99cdd8"));
        changePassword.setBackground(Color.decode("#99cdd8"));

        final JPanel buttonWrapper = new JPanel();
        buttonWrapper.setLayout(new GridLayout(buttonsRows, buttonsCols, buttonsHgap, buttonsVgap));
        buttonWrapper.setBackground(Color.decode(backGroundc));
        buttonWrapper.setOpaque(false);

        changePassword.setBackground(Color.decode("#9BA8AB"));
        changePassword.setForeground(Color.decode("#253745"));
        changePassword.setFocusPainted(false);
        changePassword.setOpaque(true);
        changePassword.setBorderPainted(false);

        logOut.setBackground(Color.decode("#9BA8AB"));
        logOut.setForeground(Color.decode("#253745"));
        logOut.setFocusPainted(false);
        logOut.setOpaque(true);
        logOut.setBorderPainted(false);

        buttonWrapper.add(changePassword);
        buttonWrapper.add(logOut);

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final LoggedInState currentState = loggedInViewModel.getState();
                        // Execute the logout through the LogoutController
                        logoutController.execute(currentState.getUsername());
                    }
                }
        );
        return buttonWrapper;
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

    public void setModeSelectionController(ModeSelectionController modeSelectionController) {
        this.modeSelectionController = modeSelectionController;
    }
}
