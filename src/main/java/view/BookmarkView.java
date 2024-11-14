package view;

import entity.Recipe;
import entity.User;
// import interface_adapter.BookmarkController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// implements ActionListener removed for now
public class BookmarkView extends RecipeListView {
    private JList<Recipe> recipeList;
    private DefaultListModel<Recipe> listModel;
    BookmarkView(User user) {
        super(user);
        for (int i = 0; i < user.getRecentlyViewed().size(); i++) {
            this.listModel.addElement(user.getBookmarks().get(i));
        }
        this.recipeList.setModel(listModel);
        setTitle(user.getUsername() + "'s Bookmarks");
    }
}
