package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import interface_adapter.ShoppingListController;

public class ShoppingListGUI {
    private final ShoppingListController controller;

    public ShoppingListGUI(ShoppingListController controller) {
        this.controller = controller;
    }

    public void run() {
        // Main Frame
        final JFrame frame = new JFrame("Shopping List Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel Layout
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Top Input Section
        final JPanel inputPanel = new JPanel(new FlowLayout());
        final JLabel ingredientLabel = new JLabel("Enter Ingredients (comma-separated):");
        final JTextField ingredientField = new JTextField(20);
        final JButton searchButton = new JButton("Search Recipes");
        inputPanel.add(ingredientLabel);
        inputPanel.add(ingredientField);
        inputPanel.add(searchButton);

        // Center Recipe List Section
        final DefaultListModel<String> recipeListModel = new DefaultListModel<>();
        final JList<String> recipeList = new JList<>(recipeListModel);
        final JScrollPane recipeScrollPane = new JScrollPane(recipeList);
        recipeScrollPane.setBorder(BorderFactory.createTitledBorder("Select Recipes"));

        // TODO Make the recipe list clickable - will be complicated...
//        recipeList.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent event) {
//                if (event.getClickCount() == 2) {
//                    // Get index of clicked item
//                    final int index = recipeList.locationToIndex(event.getPoint());
//                    // Ensure valid index
//                    if (index >= 0) {
//                        // Get selected item
//                        final String selectedItem = recipeList.getModel().getElementAt(index);
//
//                        // Convert it to a Recipe object
//                        final Recipe selectedRecipe = api.nameToRecipe(selectedItem);
//
//                        // Open a new window
//                        new IndividualRecipeView(selectedRecipe, user);
//                    }
//                }
//            }
//        });

        // Bottom Generate Section
        final JButton generateButton = new JButton("Generate Shopping List");
        final JTextArea shoppingListArea = new JTextArea(10, 50);
        shoppingListArea.setEditable(false);
        final JScrollPane shoppingListScrollPane = new JScrollPane(shoppingListArea);
        shoppingListScrollPane.setBorder(BorderFactory.createTitledBorder("Shopping List"));

        // Add components to panel
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(recipeScrollPane, BorderLayout.CENTER);
        panel.add(generateButton, BorderLayout.WEST);
        panel.add(shoppingListScrollPane, BorderLayout.SOUTH);

        // Add panel to frame
        frame.add(panel);

        // Search Button Action Listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ingredientInput = ingredientField.getText().trim();
                if (ingredientInput.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter ingredients!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Parse the user-provided ingredients
                final List<String> availableIngredients = Arrays.asList(ingredientInput.split(","));

                // Fetch recipes
                final List<String> recipes = controller.getRecipesContaining(availableIngredients);
                if (recipes.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No recipes found!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    recipeListModel.clear();
                    for (String recipe : recipes) {
                        recipeListModel.addElement(recipe);
                    }
                }
            }
        });

        // Generate Button Action Listener
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final List<String> selectedRecipes = recipeList.getSelectedValuesList();
                final String ingredientInput = ingredientField.getText().trim();

                if (ingredientInput.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter ingredients!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                List<String> availableIngredients = Arrays.asList(ingredientInput.split(","));
                if (selectedRecipes.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please select at least one recipe!", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Generate the shopping list
                final String shoppingList = controller.generateShoppingList(selectedRecipes, availableIngredients);
                shoppingListArea.setText(shoppingList);
            }
        });

        // Show Frame
        frame.pack();
        frame.setVisible(true);
    }
}
