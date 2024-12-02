package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import entity.Recipe;
import entity.User;

public class BookmarkView extends RecipeListView {

    public BookmarkView(User user, String folderName) {
        super(user, folderName);
        setTitle(user.getUsername() + "'s Bookmarks");

        final JComboBox<String> dropdown = getDropdown(user);
        add(dropdown, BorderLayout.EAST);

        final JPanel createFolderPanel = getCreateFolderPanel(user);
        add(createFolderPanel, BorderLayout.WEST);
    }

    @NotNull
    private JComboBox<String> getDropdown(User user) {
        String[] options = user.getFolders().keySet().toArray(new String[0]);
        final JComboBox<String> dropdown = new JComboBox<>(options);
        final JLabel dropdownLabel = new JLabel("Select a folder:");
        dropdown.add(dropdownLabel);

        dropdown.addActionListener(event -> {
            final String selectedOption = (String) dropdown.getSelectedItem();
            System.out.println(selectedOption + "folder name in bookmark view");
            new FolderView(user, selectedOption);
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
        return List.of();
    }
}
