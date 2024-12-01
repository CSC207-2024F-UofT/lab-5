// Commented out for now - implement if there's time
package view;

import java.util.List;

import entity.Recipe;
import entity.User;

import javax.swing.*;
// import interface_adapter.BookmarkController;

// implements ActionListener removed for now
public class FolderView extends RecipeListView {
    private final String folderName;

    public FolderView(User user, String folderName) {
        super(user);
        this.folderName = folderName;
        setTitle(folderName);
    }

    @Override
    protected List<Recipe> getRecipeList(User user1) {
        return userDAO.getFolderFromFile(user1.getUsername(), folderName);
    }
}
