package interface_adapter;

/**
 * The State information representing the recipe list.
 */
public class RecipeListState {
    private String username = "";

    private String password = "";
    private String passwordError;

    public RecipeListState() {
//        username = copy.username;
//        password = copy.password;
//        passwordError = copy.passwordError;
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

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }
}
