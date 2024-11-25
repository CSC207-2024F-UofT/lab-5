package view;

import data_access.SpoonacularRecipeDAO;
import entity.Ingredient;
import entity.Recipe;
import entity.User;
import interface_adapter.RecipeController;
import use_case.SearchRecipeUseCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import interface_adapter.ShoppingListController;
import use_case.ShoppingListUseCase;
import data_access.SpoonacularAPI;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("Welcome to the Recipe Finder");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to the Recipe Finder!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Searching Recipes");
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));
        startButton.addActionListener(e -> {
            // Open RecipeView and close HomePage
            RecipeController controller = new RecipeController(new SearchRecipeUseCase(new SpoonacularRecipeDAO()));
            new RecipeView(controller);
            dispose(); // Would it be better to not close the main page?

        });
        // Shopping List Button
        JButton shoppingListButton = new JButton("Shopping List");
        shoppingListButton.setFont(new Font("Arial", Font.PLAIN, 16));

// Action Listener for Shopping List Button
        shoppingListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Launch Shopping List GUI
                SpoonacularAPI api = new SpoonacularAPI();
                ShoppingListUseCase useCase = new ShoppingListUseCase(api);
                ShoppingListController controller = new ShoppingListController(useCase);
                ShoppingListGUI gui = new ShoppingListGUI(controller);
                gui.run();
            }
        });

        JButton bookmarksButton = new JButton("Bookmarks");
        bookmarksButton.setFont(new Font("Arial", Font.PLAIN, 16));
        bookmarksButton.addActionListener(e -> {
            // Open BookmarkView in a separate window
            User user = new User("Test_username", "Test_password");
            Ingredient ingredient1 = new Ingredient("Test_ingredient1");
            Ingredient ingredient2 = new Ingredient("Test_Ingredient2");
            String imageURL = "https://img.spoonacular.com/recipes/716429-556x370.jpg";
            Recipe recipe1 = new Recipe("name1", "url1", java.util.List.of(ingredient1, ingredient2), imageURL);
            Recipe recipe2 = new Recipe("name2", "url2", List.of(ingredient1, ingredient2), imageURL);
//            recipe1.setImage(imageURL);
//            recipe2.setImage(imageURL);
            user.addBookmark(recipe1);
            user.addBookmark(recipe2);
            new BookmarkView(user);
        });

        JButton recentlyViewedButton = new JButton("Recently Viewed");
        recentlyViewedButton.setFont(new Font("Arial", Font.PLAIN, 16));
        recentlyViewedButton.addActionListener(e -> {
            User user = new User("Test_username", "Test_password");
            Ingredient ingredient1 = new Ingredient("Test_ingredient1");
            Ingredient ingredient2 = new Ingredient("Test_Ingredient2");
            String imageURL = "https://img.spoonacular.com/recipes/716429-556x370.jpg";
            Recipe recipe1 = new Recipe("name1", "url1", java.util.List.of(ingredient1, ingredient2), imageURL);
            Recipe recipe2 = new Recipe("name2", "url2", List.of(ingredient1, ingredient2), imageURL);
//            recipe1.setImage(imageURL);
//            recipe2.setImage(imageURL);
            user.addRecentlyViewed(recipe1);
            user.addRecentlyViewed(recipe2);
            new RecentlyViewedView(user);
        });

        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.add(startButton);
        panel.add(bookmarksButton);
        panel.add(recentlyViewedButton);
        panel.add(shoppingListButton);
        add(panel, BorderLayout.SOUTH);
//        add(startButton, BorderLayout.SOUTH);
//        add(bookmarksButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}

