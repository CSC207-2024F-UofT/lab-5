package view;

import entity.Recipe;
import interface_adapter.saved_recipes.SavedrecipesState;
import interface_adapter.saved_recipes.SavedrecipesViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SavedrecipesView extends JPanel implements PropertyChangeListener {

    private final String viewName = "SavedRecipes";
    private final SavedrecipesViewModel savedrecipesViewModel;

    private final JLabel username;

    private final JButton backButton;

    private final JPanel recipeListPanel;
    private final JScrollPane scrollPane;

    public SavedrecipesView(SavedrecipesViewModel savedrecipesViewModel) {
        this.savedrecipesViewModel = savedrecipesViewModel;
        this.savedrecipesViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Saved Recipes");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel usernameLabel = new JLabel("'Saved Recipes'");
        username = new JLabel();
        usernamePanel.add(username);
        usernamePanel.add(usernameLabel);

        final JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Back");
        backButtonPanel.add(backButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernamePanel);
        this.add(backButtonPanel);

        recipeListPanel = new JPanel();
        recipeListPanel.setLayout(new BoxLayout(recipeListPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(recipeListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane);
    }

    public void populateRecipeList(Map<Recipe, Integer> recipesWithRatings) {
        recipeListPanel.removeAll();

        for (Map.Entry<Recipe, Integer> entry : recipesWithRatings.entrySet()) {
            Recipe recipe = entry.getKey();
            Integer rating = entry.getValue();

            // Create a panel for each recipe entry
            JPanel recipePanel = new JPanel();
            recipePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            recipePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Ensure width fits

            JLabel recipeLabel = new JLabel(recipe.getName() + " (Rating: " + rating + ")");
            JButton detailsButton = new JButton("Details");

            recipePanel.add(recipeLabel);
            recipePanel.add(detailsButton);

            recipeListPanel.add(recipePanel);
        }

        recipeListPanel.revalidate();
        recipeListPanel.repaint();

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final SavedrecipesState state = (SavedrecipesState) evt.getNewValue();
            username.setText(state.getUsername());

            // Update recipe list if the state has recipes
            if (state.getRecipes() != null) {
                populateRecipeList(state.getRecipes());
            }
        }
    }

    public String getViewName() {
        return viewName;
    }
    //Test fuction//
    public static void test() {
        // Create sample recipes using Java 7-compatible methods
        Map<String, Integer> pastaNutrients = new HashMap<>();
        pastaNutrients.put("Carbs", 100);
        pastaNutrients.put("Protein", 20);

        Set<String> pastaTags = new HashSet<>();
        pastaTags.add("Dinner");

        Recipe pasta = new Recipe(
                "Pasta",
                2,
                500,
                pastaNutrients,
                pastaTags,
                "https://example.com/pasta",
                "pasta_image"
        );

        Map<String, Integer> saladNutrients = new HashMap<>();
        saladNutrients.put("Fiber", 20);
        saladNutrients.put("Vitamins", 10);

        Set<String> saladTags = new HashSet<>();
        saladTags.add("Healthy");

        Recipe salad = new Recipe(
                "Salad",
                1,
                200,
                saladNutrients,
                saladTags,
                "https://example.com/salad",
                "salad_image"
        );

        Map<String, Integer> smoothieNutrients = new HashMap<>();
        smoothieNutrients.put("Sugar", 15);
        smoothieNutrients.put("Protein", 5);

        Set<String> smoothieTags = new HashSet<>();
        smoothieTags.add("Breakfast");
        smoothieTags.add("Healthy");

        Recipe smoothie = new Recipe(
                "Smoothie",
                1,
                150,
                smoothieNutrients,
                smoothieTags,
                "https://example.com/smoothie",
                "smoothie_image"
        );

        // Create a HashMap of recipes with their ratings
        HashMap<Recipe, Integer> recipes = new HashMap<>();
        recipes.put(pasta, 5);
        recipes.put(salad, 4);
        recipes.put(smoothie, 3);

        // Set up the main frame for testing
        JFrame frame = new JFrame("Test Recipe Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        // Create a mock ViewModel (can replace with your actual ViewModel implementation)
        SavedrecipesViewModel mockViewModel = new SavedrecipesViewModel() {
            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {
                // No-op for this example
            }

            public void removePropertyChangeListener(PropertyChangeListener listener) {
                // No-op for this example
            }
        };

        // Create the SavedrecipesView and populate it
        SavedrecipesView savedrecipesView = new SavedrecipesView(mockViewModel);
        savedrecipesView.populateRecipeList(recipes);

        // Add the view to the frame
        frame.add(savedrecipesView);
        frame.setVisible(true);
    }

}