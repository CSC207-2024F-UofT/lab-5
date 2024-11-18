package app;

import entity.Ingredient;
import entity.Recipe;
import entity.User;
import view.BookmarkView;
import view.LoginSignupPage;
import view.RecentlyViewedView;

import java.util.List;

public class Main {
    // mock objects to test bookmarks page
    private static User user;
    private static Recipe recipe1;
    private static Recipe recipe2;
    private static String imageURL;
    private static Ingredient ingredient1;
    private static Ingredient ingredient2;

    public static void main(String[] args) {
        // Launch the login/signup page as the first view
        new LoginSignupPage();
    }
}
