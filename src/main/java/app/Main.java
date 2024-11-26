package app;

// import view.HomePage;
import view.LoginSignupPage;

/**
 * This is where the application starts. ..
 */
public class Main {

    /**
     * The entry point of the application.
     *
     * @param args command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Launch the login/signup page as the first view
        new LoginSignupPage();

        // Simulate successful login/signup
        // Uncomment and use this logic after user authentication is implemented
        // new HomePage();
    }
}
