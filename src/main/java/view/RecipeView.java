package view;

import entity.Recipe;
import entity.User;
import interface_adapter.RecipeController;
import interface_adapter.SearchRecipePresenter;
import use_case.filter_recipes.FilterRecipesDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class RecipeView extends JFrame {
    private JTextField ingredientInput;
    private JButton searchButton;
    private JList<Recipe> recipeList;
    private final DefaultListModel<Recipe> listModel;
    private final RecipeController controller;
    private final SearchRecipePresenter presenter;
    private final User user;

    private final FilterRecipesDataAccessInterface frDataAccessInterface; // Filter functionality remains untouched
    private JComboBox<String> dietComboBox;
    private JComboBox<String> cuisineComboBox;

    public RecipeView(RecipeController controller, SearchRecipePresenter presenter, User user, FilterRecipesDataAccessInterface frDataAccessInterface) {
        this.controller = controller;
        this.presenter = presenter;
        this.user = user;
        this.frDataAccessInterface = frDataAccessInterface;

        setTitle("Recipe Generator");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        ingredientInput = new JTextField(20);
        searchButton = new JButton("Find Recipes");
        recipeList = new JList<>();
        listModel = new DefaultListModel<>();
        recipeList.setModel(listModel);

        // Add event listeners
        searchButton.addActionListener(e -> handleSearch());
        recipeList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = recipeList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        Recipe selectedRecipe = recipeList.getModel().getElementAt(index);
                        openIndividualRecipeView(selectedRecipe);
                    }
                }
            }
        });

        // Build the UI layout
        JPanel inputPanel = createInputPanel();
        JPanel filterPanel = createFilterPanel();

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(recipeList), BorderLayout.CENTER);
        add(filterPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter ingredients (comma-separated):"));
        panel.add(ingredientInput);
        panel.add(searchButton);
        return panel;
    }

    private JPanel createFilterPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        dietComboBox = new JComboBox<>();
        cuisineComboBox = new JComboBox<>();

        panel.add(new JLabel("Diet:"));
        populateDropdown(dietComboBox, frDataAccessInterface.getAvailableDiets());
        panel.add(dietComboBox);

        panel.add(new JLabel("Cuisine:"));
        populateDropdown(cuisineComboBox, frDataAccessInterface.getAvailableCuisines());
        panel.add(cuisineComboBox);

        dietComboBox.addActionListener(event -> applyFilters());
        cuisineComboBox.addActionListener(event -> applyFilters());

        return panel;
    }

    private void populateDropdown(JComboBox<String> dropdown, List<String> items) {
        dropdown.addItem("Any");
        for (String item : items) {
            dropdown.addItem(item);
        }
    }

    private void handleSearch() {
        String ingredientsText = ingredientInput.getText();
        List<String> ingredients = List.of(ingredientsText.split(","));

        // Delegate search to the controller
        controller.searchRecipes(ingredients);

        // Retrieve results from the presenter
        listModel.clear();
        for (Recipe recipe : presenter.getRecipes()) {
            listModel.addElement(recipe);
        }
    }

    private void applyFilters() {
        // This logic remains untouched for FilterRecipe functionality
        String ingredientsText = ingredientInput.getText();
        List<String> ingredients = List.of(ingredientsText.split(","));
        String selectedDiet = (String) dietComboBox.getSelectedItem();
        String selectedCuisine = (String) cuisineComboBox.getSelectedItem();

        List<Recipe> filteredRecipes = frDataAccessInterface.filterSearchRecipes(ingredients, selectedDiet, selectedCuisine);

        // Update the recipe list with filtered results
        listModel.clear();
        for (Recipe recipe : filteredRecipes) {
            listModel.addElement(recipe);
        }
    }

    private void openIndividualRecipeView(Recipe recipe) {
        // Open a detailed view for the selected recipe
        new IndividualRecipeView(recipe, user);
    }
}
