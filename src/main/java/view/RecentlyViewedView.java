package view;

import entity.Recipe;
import entity.User;

import javax.swing.*;
import java.awt.*;

// TODO make an interface or parent class for BookmarkView and RecentlyViewedView? Can be something like RecipeListView
// This View would need a button that adds a recipe to bookmarks - or maybe it should be in IndividualRecipeView
public class RecentlyViewedView extends JFrame {
    // should the search attributes be defined here?
    private JTextField searchField;
    private JButton searchButton;

    // attributes for default view
    private JList<Recipe> recentlyViewedList;
    private User user;

    // button to view individual recipes
    private JButton viewRecipeButton;
    private DefaultListModel<Recipe> listModel;

    public RecentlyViewedView(User user) {
        // TODO getBookmarks() method
        this.recentlyViewedList = new JList<Recipe>();
        this.listModel = new DefaultListModel<Recipe>();
        for (int i = 0; i < user.getRecentlyViewed().size(); i++) {
            listModel.addElement(user.getBookmarks().get(i));
        }
        this.recentlyViewedList.setModel(listModel);

        setTitle(user.getUsername() + "'s Recently Viewed");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JScrollPane(recentlyViewedList), BorderLayout.CENTER);
    }
}
