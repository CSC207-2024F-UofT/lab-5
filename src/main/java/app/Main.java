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
    private static Ingredient ingredient1;
    private static Ingredient ingredient2;

    public static void main(String[] args) {
        // Launch the login/signup page as the first view
        new LoginSignupPage();

        // Temporarily launch the bookmarks page and recently viewed page upon running the program
        user = new User("Test_username", "Test_password");
        ingredient1 = new Ingredient("Test_ingredient1");
        ingredient2 = new Ingredient("Test_Ingredient2");
        recipe1 = new Recipe("name1", "url1", List.of(ingredient1, ingredient2));
        recipe2 = new Recipe("name2", "url2", List.of(ingredient1, ingredient2));
        user.addBookmark(recipe1);
        user.addBookmark(recipe2);
        user.addRecentlyViewed(recipe1);
        user.addRecentlyViewed(recipe2);
        new BookmarkView(user);
        new RecentlyViewedView(user);
    }
}
