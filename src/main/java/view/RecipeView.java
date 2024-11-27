package view;

import entity.Recipe;
import entity.User;
import interface_adapter.RecipeController;
import interface_adapter.filter_recipes.FilterRecipesController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RecipeView extends JFrame {
    private JTextField ingredientInput;
    private JButton searchButton;
    // private JList<String> recipeList; - commented out for now to make the double click function work
    private JList <Recipe> recipeList;
    private final DefaultListModel<Recipe> listModel;
    private RecipeController controller;
    private User user;

    private final FilterRecipesController filterRecipesController;
    private final JComboBox<String> dietComboBox;
    private final JComboBox<String> cuisineComboBox;

    public RecipeView(RecipeController controller, User user, FilterRecipesController filterRecipesController) {
        this.controller = controller;
        this.user = user;
        this.filterRecipesController = filterRecipesController;
        // setFilterRecipesController(filterRecipesController);
        setTitle("Recipe Generator");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ingredientInput = new JTextField(20);
        searchButton = new JButton("Find Recipes");
        recipeList = new JList<>();
        this.listModel = new DefaultListModel<>();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ingredientsText = ingredientInput.getText();
                List<String> ingredients = Arrays.asList(ingredientsText.split(","));
                List<Recipe> recipes = controller.getRecipes(ingredients);

                // I commented this out for now and instantiated the list of recipes differently to make it clickable;
                // feel free to change it as needed
//                String[] recipeNames = recipes.stream()
//                        .map(recipe -> recipe.getName() + " - " + recipe.getUrl())
//                        .toArray(String[]::new);
//                recipeList.setListData(recipeNames);

                // final DefaultListModel<Recipe> listModel = new DefaultListModel<>();
                listModel.clear();
                for (Recipe recipe : recipes) {
                    listModel.addElement(recipe);
                }
                recipeList.setModel(listModel);

                // Make the recipe list clickable
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

                                new IndividualRecipeView(selectedItem, user);
                            }
                        }
                    }
                });
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
        filterPanel.add(dietComboBox);
        filterPanel.add(new JLabel("Cuisine:"));
        filterPanel.add(cuisineComboBox);

        dietComboBox.addActionListener(event -> applyFilters());
        cuisineComboBox.addActionListener(event -> applyFilters());

        add(filterPanel, BorderLayout.SOUTH);

        populateDropdowns();

        setVisible(true);
    }

    // populating diet and cuisine dropdown menus
    private void populateDropdowns() {
        try {
            final List<String> diets = filterRecipesController.getAvailableDiets();
            dietComboBox.addItem("Any");
            for (String diet : diets) {
                dietComboBox.addItem(diet);
            }

            final List<String> cuisines = filterRecipesController.getAvailableCuisines();
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
        final String enteredIngredients = ingredientInput.getText();
        final List<String> ingredients = List.of(enteredIngredients.split(","));

        final String selectedDiet = Objects.requireNonNull(dietComboBox.getSelectedItem()).toString();
        final String selectedCuisine = Objects.requireNonNull(cuisineComboBox.getSelectedItem()).toString();

        final List<Recipe> recipesFiltered =
                filterRecipesController.filterSearchRecipes(ingredients, selectedDiet, selectedCuisine);

        listModel.clear();
        for (Recipe recipe : recipesFiltered) {
            listModel.addElement(recipe);
        }
        recipeList.setModel(listModel);
    }

    // public void setFilterRecipesController(FilterRecipesController filterRecipesController) {
    //     this.filterRecipesController = filterRecipesController;
    // }

}
