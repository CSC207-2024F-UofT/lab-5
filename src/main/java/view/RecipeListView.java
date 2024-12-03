package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import data_access.SpoonacularRecipeDAO;
import data_access.UserDAOImpl;
import entity.Recipe;
import entity.User;
import interface_adapter.RecipeListController;
import interface_adapter.RecipeListState;
import interface_adapter.RecipeListViewModel;
import interface_adapter.search_recipe_list_by_ingredient.SearchRecipeListByIngredientController;
import interface_adapter.search_recipe_list_by_name.SearchRecipeListByNameController;
import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientUseCase;
import use_case.search_recipe_list_by_name.SearchRecipeListByNameUseCase;

public abstract class RecipeListView extends JFrame implements ActionListener, PropertyChangeListener {
    private static User user;
    protected static UserDAOImpl userDAO;
    protected final JList<Recipe> recipeList;
    protected final DefaultListModel<Recipe> listModel;
    private final RecipeListController controller;

    private JTextField ingredientSearchField;
    private JButton ingredientSearchButton;
    private JTextField recipeSearchField;
    private JButton recipeSearchButton;
    private JButton clearSearchButton;

    private JComboBox<String> dietComboBox;
    private JComboBox<String> cuisineComboBox;
    private SpoonacularRecipeDAO spoonacularRecipeDAO;

    private final String folderName;

    private final RecipeListViewModel recipeListViewModel;
    private SearchRecipeListByIngredientController searchRecipeListByIngredientController;
    private SearchRecipeListByNameController searchRecipeListByNameController;

    /*
    Generates the default view of a list of recipes associated with a User.
     */
    public RecipeListView(User user, String folderName, RecipeListViewModel recipeListViewModel) {
        this.user = user;
        this.folderName = folderName;
        this.userDAO = new UserDAOImpl();
        this.recipeList = new JList<>();
        this.listModel = new DefaultListModel<>();
        this.controller = new RecipeListController(new SearchRecipeListByIngredientUseCase(
                getRecipeList(userDAO.findUserByUsername(user.getUsername()))),
                new SearchRecipeListByNameUseCase(getRecipeList(userDAO.findUserByUsername(user.getUsername()))));
        this.spoonacularRecipeDAO = new SpoonacularRecipeDAO();

        this.recipeListViewModel = recipeListViewModel;
        this.recipeListViewModel.addPropertyChangeListener(this);

        // Initialize recipe list to display
        List<Recipe> recipes = new ArrayList<>();
        // Split into cases: BookmarkView/RecentlyViewedView and FolderView
        if (folderName == null) {
            recipes = getRecipeList(userDAO.findUserByUsername(user.getUsername()));
        }
        else {
            recipes = getRecipeList(userDAO.findUserByUsername(user.getUsername()), folderName);
        }
        for (Recipe recipe : recipes) {
            this.listModel.addElement(recipe);
        }
        this.recipeList.setModel(listModel);
        // Wrap the JList in a JScrollPane
        final JScrollPane scrollPane = new JScrollPane(recipeList);
        scrollPane.setPreferredSize(recipeList.getPreferredScrollableViewportSize());
        add(scrollPane, BorderLayout.SOUTH);

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

        // Initialize and display the two search bars in a panel
        final JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        // Search by ingredient
        ingredientSearchField = new JTextField(20);
        ingredientSearchButton = new JButton("Search recipes by ingredients");
        final JPanel ingredientSearchPanel = new JPanel();
        ingredientSearchPanel.add(new JLabel("Enter ingredients (comma-separated):"));
        ingredientSearchPanel.add(ingredientSearchField);
        ingredientSearchPanel.add(ingredientSearchButton);
        ingredientSearchButton.addActionListener(this);
        searchPanel.add(ingredientSearchPanel);
        // Search by recipe name
        recipeSearchField = new JTextField(20);
        recipeSearchButton = new JButton("Search recipes by name");
        final JPanel recipeSearchPanel = new JPanel();
        recipeSearchPanel.add(new JLabel("Enter recipe name:"));
        recipeSearchPanel.add(recipeSearchField);
        recipeSearchPanel.add(recipeSearchButton);
        recipeSearchButton.addActionListener(this);
        searchPanel.add(recipeSearchPanel);

        // Clear search button
        clearSearchButton = new JButton("Clear Search");
        clearSearchButton.addActionListener(this);
        add(clearSearchButton, BorderLayout.WEST);

        // Add the search panel to the frame
        add(searchPanel, BorderLayout.NORTH);

        // Search filters
        final JPanel filterPanel = new JPanel(new FlowLayout());
        dietComboBox = new JComboBox<>();
        cuisineComboBox = new JComboBox<>();
        filterPanel.add(new JLabel("Diet:"));
        filterPanel.add(dietComboBox);
        filterPanel.add(new JLabel("Cuisine:"));
        filterPanel.add(cuisineComboBox);

        add(filterPanel, BorderLayout.EAST);

        dietComboBox.addActionListener(e -> applyFilters());
        cuisineComboBox.addActionListener(e -> applyFilters());

        populateDropdowns();

        // dietComboBox.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         applyFilters();
        //     }
        // });

        // cuisineComboBox.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         applyFilters();
        //     }
        // });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == ingredientSearchButton) {
            final String userInput = ingredientSearchField.getText();
            final List<String> ingredients = Arrays.asList(userInput.split(","));
            // TODO double check then delete
//            List<Recipe> recipes = controller.getRecipesByIngredients(ingredients);
//            final DefaultListModel<Recipe> ingredientSearchListModel = new DefaultListModel<>();
//            for (Recipe recipe : recipes) {
//                ingredientSearchListModel.addElement(recipe);
//            }
//            recipeList.setModel(ingredientSearchListModel);

            final RecipeListState currentState = recipeListViewModel.getState();
            this.searchRecipeListByIngredientController.execute(
                    ingredients, currentState.getUser(), currentState.getFolder());
        }
        if (event.getSource() == recipeSearchButton) {
            final String userInput = recipeSearchField.getText();
            // TODO double check then delete
//            final List<Recipe> recipes = controller.getRecipesByName(userInput);
//            final DefaultListModel<Recipe> recipeSearchListModel = new DefaultListModel<>();
//            for (Recipe recipe : recipes) {
//                recipeSearchListModel.addElement(recipe);
//            }
//            recipeList.setModel(recipeSearchListModel);
            final RecipeListState currentState = recipeListViewModel.getState();
            this.searchRecipeListByNameController.execute(
                    userInput, currentState.getUser(), currentState.getFolder());
        }
        if (event.getSource() == clearSearchButton) {
            recipeList.setModel(listModel);
        }
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
        final String enteredIngredients = ingredientSearchField.getText();
        final List<String> ingredients = List.of(enteredIngredients.split(","));

        // final String selectedDiet = dietComboBox.getSelectedItem().toString();
        // final String selectedCuisine = cuisineComboBox.getSelectedItem().toString();

        // final List<Recipe> recipesFiltered =
        //         spoonacularRecipeDAO.filterSearchRecipes(ingredients, selectedDiet, selectedCuisine);

        // listModel.clear();
        // for (Recipe recipe : recipesFiltered) {
        //     listModel.addElement(recipe);
        // }
        // recipeList.setModel(listModel);
    }

    protected abstract List<Recipe> getRecipeList(User user1);

    protected abstract List<Recipe> getRecipeList(User user1, String folderName);

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("state")) {
            final RecipeListState state = (RecipeListState) event.getNewValue();
            // TODO recipeList.setModel(ingredientSearchListModel);
        }
        // TODO add other cases
    }

    // TODO only need these if there's an appbuilder
    public void setSearchRecipeListByIngredientController(
            SearchRecipeListByIngredientController SearchRecipeListByIngredientController) {
        this.searchRecipeListByIngredientController = SearchRecipeListByIngredientController;
    }

    public void setSearchRecipeListByNameController(
            SearchRecipeListByNameController SearchRecipeListByNameController) {
        this.searchRecipeListByNameController = SearchRecipeListByNameController;
    }
}
