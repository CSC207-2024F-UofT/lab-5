package app;

import entity.User;
import view.BookmarkView;
import view.LoginSignupPage;

public class Main {
    // mock user object to test bookmarks page
    private static User user;

    public static void main(String[] args) {
        // Launch the login/signup page as the first view
        new LoginSignupPage();

        // Temporarily launch the bookmarks page upon running the program
        user = new User("Test_username", "Test_password");
        new BookmarkView(user);
    }
}
