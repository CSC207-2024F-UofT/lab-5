package view;

import entity.Recipe;
import interface_adapter.ShoppingListController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

public class ShoppingListGUI {
    private final ShoppingListController controller;

    public ShoppingListGUI(ShoppingListController controller) {
        this.controller = controller;
    }

    public void run() {
        // Main Frame
        JFrame frame = new JFrame("Shopping List Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        // Panel Layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Top Input Section
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel ingredientLabel = new JLabel("Enter Ingredients (comma-separated):");
        JTextField ingredientField = new JTextField(20);
        JButton searchButton = new JButton("Search Recipes");
        inputPanel.add(ingredientLabel);
        inputPanel.add(ingredientField);
        inputPanel.add(searchButton);

        // Center Recipe List Section
        DefaultListModel<String> recipeListModel = new DefaultListModel<>();
        JList<String> recipeList = new JList<>(recipeListModel);
        JScrollPane recipeScrollPane = new JScrollPane(recipeList);
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
        JButton generateButton = new JButton("Generate Shopping List");
        JTextArea shoppingListArea = new JTextArea(10, 50);
        shoppingListArea.setEditable(false);
        JScrollPane shoppingListScrollPane = new JScrollPane(shoppingListArea);
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
                    return;
                }

                // Parse the user-provided ingredients
                List<String> availableIngredients = Arrays.asList(ingredientInput.split(","));

                // Fetch recipes
                List<String> recipes = controller.getRecipesContaining(availableIngredients);
                if (recipes.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No recipes found!", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
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
                List<String> selectedRecipes = recipeList.getSelectedValuesList();
                String ingredientInput = ingredientField.getText().trim();

                if (ingredientInput.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter ingredients!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<String> availableIngredients = Arrays.asList(ingredientInput.split(","));
                if (selectedRecipes.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please select at least one recipe!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Generate the shopping list
                String shoppingList = controller.generateShoppingList(selectedRecipes, availableIngredients);
                shoppingListArea.setText(shoppingList);
            }
        });

        // Show Frame
        frame.setVisible(true);
    }
}
