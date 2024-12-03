package interface_adapter.change_password;

/**
 * Represents the state of the Change Password feature.
 * Stores the username, old password, new password, and any status messages.
 */
public class ChangePasswordState {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String statusMessage;

    /**
     * Constructs a ChangePasswordState with the given username, old password, new password, and status message.
     *
     * @param username The username associated with the current state.
     * @param oldPassword The old password of the user.
     * @param newPassword The new password to be set for the user.
     * @param statusMessage The status message for the change password operation.
     */
    public ChangePasswordState(String username, String oldPassword, String newPassword, String statusMessage) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.statusMessage = statusMessage;
    }

    public ChangePasswordState(ChangePasswordState copy) {
        this.username = copy.username;
        this.oldPassword = copy.oldPassword;
        this.newPassword = copy.newPassword;
        this.statusMessage = copy.statusMessage;
    }

    // Default constructor.
    public ChangePasswordState() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
