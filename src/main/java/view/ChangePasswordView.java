package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;

/**
 * The View for the Change Password Use Case.
 */
public class ChangePasswordView extends JPanel implements PropertyChangeListener {

    private static final int FIELD_WIDTH = 15;
    private final String viewName = "change_password";
    private final ChangePasswordViewModel changePasswordViewModel;
    private ChangePasswordController changePasswordController;
    private final JTextField usernameField;
    private final JPasswordField oldPasswordField;
    private final JPasswordField newPasswordField;
    private final JButton changePasswordButton;
    private final JButton backButton;

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.changePasswordViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(ChangePasswordViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel usernameLabel = new JLabel(ChangePasswordViewModel.USERNAME_LABEL);
        usernameField = new JTextField(FIELD_WIDTH);

        final JLabel oldPasswordLabel = new JLabel(ChangePasswordViewModel.OLD_PASSWORD_LABEL);
        oldPasswordField = new JPasswordField(FIELD_WIDTH);

        final JLabel newPasswordLabel = new JLabel(ChangePasswordViewModel.NEW_PASSWORD_LABEL);
        newPasswordField = new JPasswordField(FIELD_WIDTH);

        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(oldPasswordLabel);
        inputPanel.add(oldPasswordField);
        inputPanel.add(newPasswordLabel);
        inputPanel.add(newPasswordField);

        final JPanel buttons = new JPanel();
        changePasswordButton = new JButton(ChangePasswordViewModel.CHANGE_PASSWORD_BUTTON_LABEL);
        buttons.add(changePasswordButton);
        backButton = new JButton(ChangePasswordViewModel.BACK_BUTTON_LABEL);
        buttons.add(backButton);

        changePasswordButton.addActionListener(evt -> {
            final String username = usernameField.getText();
            final String oldPassword = new String(oldPasswordField.getPassword());
            final String newPassword = new String(newPasswordField.getPassword());

            if (!username.isEmpty() && !oldPassword.isEmpty() && !newPassword.isEmpty()) {
                changePasswordController.execute(newPassword, username);
            }
        });

        backButton.addActionListener(evt -> changePasswordController.switchToBookmarkView());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(inputPanel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
        if (state != null) {
            if (state.getStatusMessage() != null && !state.getStatusMessage().isEmpty()) {
                JOptionPane.showMessageDialog(this, state.getStatusMessage());
            }
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController controller) {
        this.changePasswordController = controller;
    }
}
