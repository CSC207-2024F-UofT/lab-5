package view;

import java.util.List;

import javax.swing.*;

import entity.Recipe;
import entity.User;
import interface_adapter.RecipeListViewModel;

public class RecentlyViewedView extends RecipeListView {

    public RecentlyViewedView(User user, String folderName, RecipeListViewModel recipeListViewModel) {
        super(user, folderName, recipeListViewModel);
        setTitle(user.getUsername() + "'s Recently Viewed");
        final JButton clearButton = new JButton("Clear Recently Viewed");
        clearButton.addActionListener(event -> {
            userDAO.clearRecentlyViewed(user.getUsername());
            recipeList.setModel(listModel);
        });
        add(clearButton);
    }

    @Override
    protected List<Recipe> getRecipeList(User user1) {
        return userDAO.getRecentlyViewedFromFile(user1.getUsername());
    }

    @Override
    protected List<Recipe> getRecipeList(User user1, String folderName) {
        return userDAO.getRecentlyViewedFromFile(user1.getUsername());
    }
}
