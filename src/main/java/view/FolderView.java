// Commented out for now - implement if there's time
package view;

import java.util.List;

import entity.Recipe;
import entity.User;

import javax.swing.*;
// import interface_adapter.BookmarkController;

// implements ActionListener removed for now
public class FolderView extends RecipeListView {
//    private final List<Recipe> recipes;

    public FolderView(User user, String folderName) {
        super(user, folderName);
//        this.recipes = getRecipeList(userDAO.findUserByUsername(user.getUsername()), folderName);
        setTitle(folderName);
    }

    @Override
    protected List<Recipe> getRecipeList(User user1) {
        return List.of();
    }

    @Override
    protected List<Recipe> getRecipeList(User user1, String folder) {
        System.out.println(user1.getUsername());
        System.out.println(folder + "aaaaa");
        return userDAO.getFolderFromFile(user1.getUsername(), folder);
    }
}
