package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import entity.Recipe;
import entity.User;
import interface_adapter.RecipeListController;
import use_case.SearchRecipeListUseCase;

public abstract class RecipeListView extends JFrame implements ActionListener {
    // attributes for default view
    private static User user;
    private final JList<Recipe> recipeList;
    private final DefaultListModel<Recipe> listModel;
    private final RecipeListController controller;

    // TODO attributes for other functionalities...
    private JTextField ingredientSearchField;
    private JButton ingredientSearchButton;
    private JTextField recipeSearchField;
    private JButton recipeSearchButton;

    /*
    Generates the default view of a list of recipes associated with a User.
     */
    public RecipeListView(User user) {
        this.user = user;
        this.recipeList = new JList<>();
        this.listModel = new DefaultListModel<>();
        this.controller = new RecipeListController(new SearchRecipeListUseCase(getRecipeList(user)));

        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final List<Recipe> recipes = getRecipeList(user);
        for (Recipe recipe : recipes) {
            this.listModel.addElement(recipe);
        }

        this.recipeList.setModel(listModel);

        // Wrap the JList in a JScrollPane
        final JScrollPane scrollPane = new JScrollPane(recipeList);
        scrollPane.setPreferredSize(recipeList.getPreferredScrollableViewportSize());
        add(scrollPane, BorderLayout.SOUTH);

        // TODO fix the layout
//        // Main panel with vertical BoxLayout
//        final JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        setContentPane(mainPanel);
//
//        // Add components to the main panel
//        mainPanel.add(scrollPane);

        recipeList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    // Get index of clicked item
                    final int index = recipeList.locationToIndex(event.getPoint());
                    // Ensure valid index
                    if (index >= 0) {
                        // Get selected item
                        final Recipe selectedItem = recipeList.getModel().getElementAt(index);
                        // Open a new window
                        new IndividualRecipeView(selectedItem);
                    }
                }
            }
        });

        // Display the two search bars
        ingredientSearchField = new JTextField(20);
        ingredientSearchButton = new JButton("Search recipes by ingredients");
        final JPanel ingredientSearchPanel = new JPanel();
        ingredientSearchPanel.add(new JLabel("Enter ingredients (comma-separated):"));
        ingredientSearchPanel.add(ingredientSearchField);
        ingredientSearchPanel.add(ingredientSearchButton);
        ingredientSearchButton.addActionListener(this);
        add(ingredientSearchPanel, BorderLayout.NORTH);

        recipeSearchField = new JTextField(20);
        recipeSearchButton = new JButton("Search recipes by name");
        final JPanel recipeSearchPanel = new JPanel();
        recipeSearchPanel.add(new JLabel("Enter recipe name:"));
        recipeSearchPanel.add(recipeSearchField);
        recipeSearchPanel.add(recipeSearchButton);
        recipeSearchButton.addActionListener(this);
        add(recipeSearchPanel, BorderLayout.CENTER);

        // TODO Search function - in progress
//        ingredientInput = new JTextField(20);
//        searchButton = new JButton("Find Recipes by Ingredients");
//        recipeListByIngredient = new JList<>();
//
//        nameInput = new JTextField(20);
//        searchButton = new JButton("Find Recipes by Name");
//        recipeListByName = new JList<>();
//
//        searchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String ingredientsText = ingredientInput.getText();
//                java.util.List<String> ingredients = Arrays.asList(ingredientsText.split(","));
//                List<Recipe> recipes = controller.getRecipes(ingredients);
//
//                String[] recipeNames = recipes.stream()
//                        .map(recipe -> recipe.getName() + " - " + recipe.getUrl())
//                        .toArray(String[]::new);
//                recipeList.setListData(recipeNames);
//            }
//        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        // temporary display for search button
        if (event.getSource() == ingredientSearchButton) {
            final String userInput = ingredientSearchField.getText();
            // TODO - replace: Display the input in the result label
            JOptionPane.showMessageDialog(this, userInput);

            // TODO complete search function...

            final List<String> ingredients = Arrays.asList(userInput.split(","));
            // ^ TODO need to convert user input to Ingredient objects!
            // final List<Recipe> recipes = controller.getRecipes(ingredients);
//
//        final DefaultListModel<Recipe> listModel = new DefaultListModel<>();
//        for (Recipe recipe : recipes) {
//            listModel.addElement(recipe);
//        }
//        recipeList.setModel(listModel);
//
//        // Make the recipe list clickable
//        recipeList.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent event) {
//                if (event.getClickCount() == 2) {
//                    // Get index of clicked item
//                    final int index = recipeList.locationToIndex(event.getPoint());
//                    // Ensure valid index
//                    if (index >= 0) {
//                        // Get selected item
//                        final Recipe selectedItem = recipeList.getModel().getElementAt(index);
//                        // Open a new window
//                        new IndividualRecipeView(selectedItem);
//                    }
//                }
//            }
//        });
        }
        if (event.getSource() == recipeSearchButton) {
            // TODO convert this into a use case
            final String userInput = recipeSearchField.getText();
            final List<Recipe> recipes = getRecipeList(user);
            final List<Recipe> results = new ArrayList<>();
            final DefaultListModel<Recipe> resultListModel = new DefaultListModel<>();
            final JList<Recipe> resultList = new JList<>(listModel);
            // TODO search by name function...
            for (Recipe recipe : recipes) {
                if (recipe.getName().contains(userInput)) {
                    results.add(recipe);
                }
            }
            // temporary display
            JOptionPane.showMessageDialog(this, results);
//            for (Recipe result : results) {
//                resultListModel.addElement(result);
//            }
//            resultList.setModel(resultListModel);
//            final JFrame resultFrame = new JFrame();
//            resultFrame.setTitle("Search results");
//            resultFrame.add(resultList);
//            setVisible(true);
        }
    }

    protected abstract List<Recipe> getRecipeList(User user1);
}
