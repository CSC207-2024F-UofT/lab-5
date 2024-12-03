package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import data_access.AppConstants;
import data_access.SpoonacularAPI;
import data_access.SpoonacularRecipeDAO;
import entity.User;
import interface_adapter.RecipeController;
import interface_adapter.RecipeListViewModel;
import interface_adapter.SearchRecipePresenter;
import interface_adapter.ShoppingListController;
import interface_adapter.filter_recipes.FilterRecipesController;
import use_case.ShoppingListUseCase;
import use_case.filter_recipes.FilterRecipesInteractor;
import use_case.search_recipe.SearchRecipeUseCase;

public class HomePage extends JFrame {
    private final User user;

    public HomePage(User user) {
        this.user = user;
        setTitle("Welcome to the Recipe Finder");
        // setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        final JLabel welcomeLabel = new JLabel("Welcome to the Recipe Finder!", JLabel.CENTER);
        welcomeLabel.setFont(new Font(AppConstants.FONT, Font.BOLD, AppConstants.WELCOME_FONT_SIZE));
        add(welcomeLabel, BorderLayout.CENTER);

        final JButton startButton = new JButton("Start Searching Recipes");
        startButton.setFont(new Font(AppConstants.FONT, Font.PLAIN, AppConstants.BUTTON_FONT_SIZE));
        startButton.addActionListener(e -> {
            // Create the presenter
            final SearchRecipePresenter presenter = new SearchRecipePresenter();
            // Create the use case interactor
            final SearchRecipeUseCase interactor = new SearchRecipeUseCase(new SpoonacularRecipeDAO(), presenter);
            // Create the controller
            final RecipeController controller = new RecipeController(interactor);
            // Open the RecipeView and pass dependencies
            // new RecipeView(controller, presenter, user, new SpoonacularRecipeDAO());
            final FilterRecipesInteractor filterInteractor = new FilterRecipesInteractor(new SpoonacularRecipeDAO());
            final FilterRecipesController filterController = new FilterRecipesController(filterInteractor);
            new RecipeView(controller, presenter, user, filterController, new SpoonacularRecipeDAO());
        });

        // Shopping List Button
        final JButton shoppingListButton = new JButton("Shopping List");
        shoppingListButton.setFont(new Font(AppConstants.FONT, Font.PLAIN, AppConstants.BUTTON_FONT_SIZE));
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

        // Bookmarks Button
        final JButton bookmarksButton = new JButton("Bookmarks");
        bookmarksButton.setFont(new Font(AppConstants.FONT, Font.PLAIN, AppConstants.BUTTON_FONT_SIZE));
        bookmarksButton.addActionListener(event -> {
            new BookmarkView(this.user, "bookmarks", new RecipeListViewModel());
        });

        // Recently Viewed Button
        final JButton recentlyViewedButton = new JButton("Recently Viewed");
        recentlyViewedButton.setFont(new Font(AppConstants.FONT, Font.PLAIN, AppConstants.BUTTON_FONT_SIZE));
        recentlyViewedButton.addActionListener(event -> {
            new RecentlyViewedView(this.user, "recentlyViewed", new RecipeListViewModel());
        });

        // Putting the buttons together
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.add(startButton);
        panel.add(bookmarksButton);
        panel.add(recentlyViewedButton);
        panel.add(shoppingListButton);
        add(panel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }
}

