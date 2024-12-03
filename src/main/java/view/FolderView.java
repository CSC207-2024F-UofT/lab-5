package view;

import java.util.List;

import entity.Recipe;
import entity.User;
import interface_adapter.RecipeListViewModel;

public class FolderView extends RecipeListView {

    public FolderView(User user, String folderName, RecipeListViewModel recipeListViewModel) {
        super(user, folderName, recipeListViewModel);
        setTitle(folderName);
    }

    @Override
    protected List<Recipe> getRecipeList(User user1) {
        return List.of();
    }

    @Override
    protected List<Recipe> getRecipeList(User user1, String folder) {
        return userDAO.getFolderFromFile(user1.getUsername(), folder);
    }
}
