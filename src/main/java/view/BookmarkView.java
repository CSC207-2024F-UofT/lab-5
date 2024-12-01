package view;

import java.util.List;

import entity.Recipe;
import entity.User;

import javax.swing.*;

public class BookmarkView extends RecipeListView {

    public BookmarkView(User user) {
        super(user);
        setTitle(user.getUsername() + "'s Bookmarks");

        // Create an array of dropdown options
        // TODO populate the list of options as the user creates folders
        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};

        // Create a JComboBox with the options
        final JComboBox<String> dropdown = new JComboBox<>(options);

        // Add an action listener to handle selections
        dropdown.addActionListener(event -> {
            final String selectedOption = (String) dropdown.getSelectedItem();
            JOptionPane.showMessageDialog(this, selectedOption);
            // TODO implement FolderView
            new FolderView(user, selectedOption);
        });

        // Add the dropdown to the frame
        add(dropdown);

        // Set the frame's layout and visibility
//        frame.setLayout(new java.awt.FlowLayout());
//        frame.setVisible(true);
    }

    @Override
    protected List<Recipe> getRecipeList(User user1) {
        return userDAO.getBookmarksFromFile(user1.getUsername());
    }
}
