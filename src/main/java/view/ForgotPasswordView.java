package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jetbrains.annotations.NotNull;

import interface_adapter.forgotPassword.ForgotPasswordState;
import interface_adapter.forgotPassword.ForgotPasswordViewModel;

/**
 * The View for forgetpassword window. This fuctionality was cut and not implemented.
 */
public class ForgotPasswordView extends JPanel implements PropertyChangeListener {

    private final String viewName = "Forgot Password";
    private final ForgotPasswordViewModel forgotPasswordViewModel;

    private final JLabel securityquestion;

    private final JTextField usernameInputField = new JTextField(15);
    private final JTextField securityanswerInputField = new JTextField(15);
    private final JTextField passwordInputField = new JTextField(15);
    private final JButton setPassword;

    public ForgotPasswordView(ForgotPasswordViewModel forgotPasswordViewModel) {
        this.forgotPasswordViewModel = forgotPasswordViewModel;
        this.forgotPasswordViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Forgot Password");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel usernamePanel = new JPanel();
        final JLabel usernameLabel = new JLabel("Username:");
        usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
        usernameLabelSetup(usernameLabel, usernameInputField, usernamePanel);

        final JPanel securityquestionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JLabel securityquestionInfo = new JLabel("Security Question");
        securityquestion = new JLabel();
        securityquestionPanel.add(securityquestion);
        securityquestionPanel.add(securityquestionInfo);

        final JPanel securityanswerPanel = new JPanel();
        final JLabel securityanswerLabel = new JLabel("security question Answer");
        securityPanelSetup(securityanswerPanel, securityanswerLabel);

        final JPanel passwordPanel = getPasswordPanel();

        final JPanel button = new JPanel();
        setPassword = new JButton("Set Password");
        button.add(setPassword);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernamePanel);
        this.add(securityquestionPanel);
        this.add(securityanswerPanel);
        this.add(passwordPanel);

        this.add(button);
    }

    @NotNull
    private JPanel getPasswordPanel() {
        final JPanel passwordPanel = new JPanel();
        final JLabel passwordLabel = new JLabel("New Password");
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordInputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordInputField.setMaximumSize(passwordInputField.getPreferredSize());
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordInputField);
        return passwordPanel;
    }

    private void securityPanelSetup(JPanel securityanswerPanel, JLabel securityanswerLabel) {
        securityanswerPanel.setLayout(new BoxLayout(securityanswerPanel, BoxLayout.Y_AXIS));
        securityanswerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        securityanswerInputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        securityanswerInputField.setMaximumSize(securityanswerInputField.getPreferredSize());
        securityanswerPanel.add(securityanswerLabel);
        securityanswerPanel.add(securityanswerInputField);
    }

    private void usernameLabelSetup(JLabel usernameLabel, JTextField usernameInputField1, JPanel usernamePanel) {
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameInputField1.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameInputField1.setMaximumSize(usernameInputField1.getPreferredSize());
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameInputField1);
    }

    // Below is Non-implemented

    /**
     * Unimplemented button.
     * @param evt Property change parameter
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("password")) {
            final ForgotPasswordState state = (ForgotPasswordState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

}
