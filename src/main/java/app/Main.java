package app;

import entity.Recipe;
import entity.User;
import view.BookmarkView;
import view.LoginSignupPage;

import java.util.List;

public class Main {
    // mock objects to test bookmarks page
    private static User user;
    private static Recipe recipe1;
    private static Recipe recipe2;

    public static void main(String[] args) {
        // Launch the login/signup page as the first view
        new LoginSignupPage();

        // Temporarily launch the bookmarks page upon running the program
        user = new User("Test_username", "Test_password");
        recipe1 = new Recipe("name1", "url1", List.of("a", "b", "c"));
        recipe2 = new Recipe("name2", "url2", List.of("a", "b", "c"));
        user.addBookmark(recipe1);
        user.addBookmark(recipe2);
        new BookmarkView(user);
    }
}
