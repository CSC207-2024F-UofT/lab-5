package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

import java.awt.*;
import javax.swing.*;

import entity.Recipe;
import entity.User;
import interface_adapter.RecipeController;
import interface_adapter.SearchRecipePresenter;
import use_case.filter_recipes.FilterRecipesDataAccessInterface;

public class RecipeView extends JFrame {
    private JTextField ingredientInput;
    private JButton searchButton;
    // private JList<String> recipeList; - commented out for now to make the double click function work
    private JList<Recipe> recipeList;
    private final DefaultListModel<Recipe> listModel;
    private final RecipeController controller;
    private final SearchRecipePresenter presenter;
    private final User user;

    private FilterRecipesDataAccessInterface frDataAccessInterface;
    private JComboBox<String> dietComboBox;
    private JComboBox<String> cuisineComboBox;
    private final String defaultFilter;

    public RecipeView(RecipeController controller, SearchRecipePresenter presenter, User user,
                      FilterRecipesDataAccessInterface frDataAccessInterface) {
        this.controller = controller;
        this.presenter = presenter;
        this.user = user;
        this.frDataAccessInterface = frDataAccessInterface;
        this.defaultFilter = "Any";

        setTitle("Recipe Generator");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ingredientInput = new JTextField(20);
        searchButton = new JButton("Find Recipes");
        recipeList = new JList<>();
        listModel = new DefaultListModel<>();
        recipeList.setModel(listModel);

        searchButton.addActionListener(event -> handleSearch());
        recipeList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    // Get index of clicked item
                    final int index = recipeList.locationToIndex(event.getPoint());
                    // Ensure valid index
                    if (index >= 0) {
                        // Get selected item
                        final Recipe selectedItem = recipeList.getModel().getElementAt(index);
                        // Open a new window
                        new IndividualRecipeView(selectedItem, user);
                    }
                }
            }
        });

        final JPanel panel = new JPanel();
        panel.add(new JLabel("Enter ingredients (comma-separated):"));
        panel.add(ingredientInput);
        panel.add(searchButton);
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(recipeList), BorderLayout.CENTER);

        // search filters
        final JPanel filterPanel = new JPanel(new FlowLayout());
        dietComboBox = new JComboBox<>();
        cuisineComboBox = new JComboBox<>();

        filterPanel.add(new JLabel("Diet:"));
        final List<String> diets = frDataAccessInterface.getAvailableDiets();
        populateDropdown(dietComboBox, diets);
        filterPanel.add(dietComboBox);

        filterPanel.add(new JLabel("Cuisine:"));
        final List<String> cuisines = frDataAccessInterface.getAvailableCuisines();
        populateDropdown(cuisineComboBox, cuisines);
        filterPanel.add(cuisineComboBox);

        add(filterPanel, BorderLayout.SOUTH);

        dietComboBox.addActionListener(event -> applyFilters());
        cuisineComboBox.addActionListener(event -> applyFilters());

        setVisible(true);
    }

    private void handleSearch() {
        String ingredientsText = ingredientInput.getText();
        List<String> ingredients = Arrays.asList(ingredientsText.split(","));
        listModel.clear();
        // Delegate search to the controller when default filters applied
        if (defaultFilter.equals(dietComboBox.getSelectedItem())
                && defaultFilter.equals(cuisineComboBox.getSelectedItem())) {
            controller.searchRecipes(ingredients);
            // Retrieve results from the presenter
            for (Recipe recipe : presenter.getRecipes()) {
                listModel.addElement(recipe);
            }
            recipeList.setModel(listModel);
        }
        else {
            // else: filters have been chosen, so using search function specific to filters
            final List<Recipe> recipes = applyFilters();
            for (Recipe recipe : recipes) {
                listModel.addElement(recipe);
            }
        }
        recipeList.setModel(listModel);
    }

    private void populateDropdown(JComboBox<String> dropdown, List<String> stringList) {
        dropdown.addItem(defaultFilter);
        for (String item : stringList) {
            dropdown.addItem(item);
        }
    }

    // applying the filters
    private List<Recipe> applyFilters() {
        final String enteredIngredients = ingredientInput.getText();
        final List<String> ingredients = List.of(enteredIngredients.split(","));
        // get selected value from dropdown menu
        final String selectedDiet = String.valueOf(dietComboBox.getSelectedItem());
        final String selectedCuisine = String.valueOf(cuisineComboBox.getSelectedItem());
        // call the data access object and api to return a list of recipes
        final List<Recipe> recipesFiltered =
                frDataAccessInterface.filterSearchRecipes(ingredients, selectedDiet, selectedCuisine);
        return recipesFiltered;
    }
}
