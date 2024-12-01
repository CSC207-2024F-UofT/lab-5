package interface_adapter.forgotPassword;

/**
 * View state for unimplemented functionality.
 */
public class ForgotPasswordState {
    private String username = "";

    private String password = "";

    public ForgotPasswordState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
