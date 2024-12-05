package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import interface_adapter.RecipeListViewModel;
import org.jetbrains.annotations.NotNull;

import entity.Recipe;
import entity.User;

public class BookmarkView extends RecipeListView {

    public BookmarkView(User user, String folderName, RecipeListViewModel recipeListViewModel) {
        super(user, folderName, recipeListViewModel);
        setTitle(user.getUsername() + "'s Bookmarks");

        final JPanel folderPanel = new JPanel();
        folderPanel.setLayout(new BoxLayout(folderPanel, BoxLayout.Y_AXIS));

        final JComboBox<String> dropdown = getDropdown(user);
        folderPanel.add(dropdown);

        final JPanel createFolderPanel = getCreateFolderPanel(user);
        folderPanel.add(createFolderPanel);

        add(folderPanel);
        pack();
    }

    @NotNull
    private JComboBox<String> getDropdown(User user) {
        String[] options = user.getFolders().keySet().toArray(new String[0]);
        final JComboBox<String> dropdown = new JComboBox<>(options);
        final JLabel dropdownLabel = new JLabel("Select a folder:");
        dropdown.add(dropdownLabel);

        dropdown.addActionListener(event -> {
            final String selectedOption = (String) dropdown.getSelectedItem();
            new FolderView(user, selectedOption, new RecipeListViewModel());
        });
        return dropdown;
    }

    @NotNull
    private JPanel getCreateFolderPanel(User user) {
        final JPanel createFolderPanel = new JPanel();
        createFolderPanel.setLayout(new BoxLayout(createFolderPanel, BoxLayout.X_AXIS));
        final JTextField textField = new JTextField(20);
        final JButton addFolderButton = new JButton("Create Folder");
        addFolderButton.addActionListener(event -> {
            final String folderName = textField.getText();
            userDAO.addFolderToFile(user.getUsername(), folderName);
            JOptionPane.showMessageDialog(null, "Folder created successfully");
        });
        createFolderPanel.add(textField);
        createFolderPanel.add(addFolderButton);
        return createFolderPanel;
    }

    @Override
    protected List<Recipe> getRecipeList(User user1) {
        return userDAO.getBookmarksFromFile(user1.getUsername());
    }

    @Override
    protected List<Recipe> getRecipeList(User user1, String folderName) {
        return userDAO.getRecentlyViewedFromFile(user1.getUsername());
    }
}
