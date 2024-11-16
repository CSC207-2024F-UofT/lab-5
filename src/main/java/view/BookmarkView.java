package view;

import java.util.List;

import entity.Recipe;
import entity.User;

import javax.swing.*;
// import interface_adapter.BookmarkController;

// implements ActionListener removed for now
public class BookmarkView extends RecipeListView {

    public BookmarkView(User user) {
        super(user);
        setTitle(user.getUsername() + "'s Bookmarks");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    protected List<Recipe> getRecipeList(User user1) {
        return user1.getBookmarks();
    }
}
