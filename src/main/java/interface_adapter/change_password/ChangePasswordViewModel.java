package interface_adapter.change_password;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Change Password Use Case.
 * Notifies the view of changes to the change password state.
 */
public class ChangePasswordViewModel extends ViewModel<ChangePasswordState> {

    public static final String TITLE_LABEL = "Change Password";
    public static final String USERNAME_LABEL = "Username:";
    public static final String OLD_PASSWORD_LABEL = "Old Password:";
    public static final String NEW_PASSWORD_LABEL = "New Password:";
    public static final String CHANGE_PASSWORD_BUTTON_LABEL = "Change Password";
    public static final String BACK_BUTTON_LABEL = "Back";

    public ChangePasswordViewModel() {
        super("change_password");
        setState(new ChangePasswordState());
    }
}
