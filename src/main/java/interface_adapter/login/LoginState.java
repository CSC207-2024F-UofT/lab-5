package interface_adapter.login;

import entity.User;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private User user;
    private String username = "";
    private String loginError;
    private String password = "";

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
