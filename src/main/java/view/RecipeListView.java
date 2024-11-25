package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

import data_access.SpoonacularRecipeDAO;
import entity.Recipe;
import entity.User;

public abstract class RecipeListView extends JFrame implements ActionListener {
    // attributes for default view
    private final JList<Recipe> recipeList;
    private final DefaultListModel<Recipe> listModel;

    // TODO attributes for other functionalities...
    private JTextField searchField;
    private JButton searchButton;
    // private BookmarkController controller;

    private JComboBox<String> dietComboBox;
    private JComboBox<String> cuisineComboBox;
    private SpoonacularRecipeDAO spoonacularRecipeDAO;

    /*
    Generates the default view of a list of recipes associated with a User.
     */
    public RecipeListView(User user) {
        this.recipeList = new JList<>();
        this.listModel = new DefaultListModel<>();

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
        add(scrollPane);

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

        // Display search bar
        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        final JPanel panel = new JPanel();
        panel.add(new JLabel("Enter ingredients (comma-separated):"));
        panel.add(searchField);
        // TODO add ActionListener for the search button
        panel.add(searchButton);
        searchButton.addActionListener(this);
        add(panel, BorderLayout.NORTH);

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

        // search filters
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        dietComboBox = new JComboBox<>();
        cuisineComboBox = new JComboBox<>();
        bottomPanel.add(new JLabel("Diet:"));
        bottomPanel.add(dietComboBox);
        bottomPanel.add(new JLabel("Cuisine:"));
        bottomPanel.add(cuisineComboBox);

        add(bottomPanel, BorderLayout.SOUTH);

        populateDropdowns();

        dietComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilters();
            }
        });

        cuisineComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyFilters();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        // temporary display for search button
        String userInput = searchField.getText();
        // Display the input in the result label
        JOptionPane.showMessageDialog(this, userInput);
    }

    // populating diet and cuisine dropdown menus
    private void populateDropdowns() {
        try {
            final List<String> diets = spoonacularRecipeDAO.getAvailableDiets();
            dietComboBox.addItem("Any");
            for (String diet : diets) {
                dietComboBox.addItem(diet);
            }

            final List<String> cuisines = spoonacularRecipeDAO.getAvailableCuisines();
            cuisineComboBox.addItem("Any");
            for (String cuisine : cuisines) {
                cuisineComboBox.addItem(cuisine);
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this,
                    "Error loading filters: " + exception.getMessage());
        }
    }

    // applying the filters
    private void applyFilters() {
        final String enteredIngredients = searchField.getText();
        final List<String> ingredients = List.of(enteredIngredients.split(","));
        // if (ingredient.isEmpty()) {
        // no initial search
        // }

        final String selectedDiet = dietComboBox.getSelectedItem().toString();
        final String selectedCuisine = cuisineComboBox.getSelectedItem().toString();

        // try {
        //      if (("Any".equals(selectedDiet)) && ("Any".equals(selectedCuisine))) {
        //          List<Recipe> recipesFiltered = spoonacularRecipeDAO.getRecipesByIngredients(ingredients);
        //      } else if ("Any".equals(selectedDiet)) {
        //          List<Recipe> recipesFiltered =
        //                  spoonacularRecipeDAO.getRecipesByIngredientsDiets(ingredients, selectedDiet);
        //      } else if ("Any".equals(selectedCuisine)) {
        //          List<Recipe> recipesFiltered =
        //                  spoonacularRecipeDAO.getRecipesByIngredientsCuisine(ingredients, selectedCuisine);
        //      }
        //
        //      listModel.clear();
        //      for (Recipe recipe : recipesFiltered) {
        //          listModel.addElement(recipe);
        //      }
        //  }

        // TODO try to make above implementation work
        // final List<Recipe> recipes = spoonacularRecipeDAO.searchRecipes(ingredients,
        //         "Any".equals(selectedDiet) ? null : selectedDiet,
        //         "Any".equals(selectedCuisine) ? null : selectedCuisine
        // );
        // Update recipe list
        // for (Recipe recipe : recipes) {
        //     listModel.addElement(recipe);
        // }
        // recipeList.setModel(listModel);

    }

    protected abstract List<Recipe> getRecipeList(User user1);
}
