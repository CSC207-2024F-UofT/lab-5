package view;

import entity.Recipe;
import interface_adapter.search_results.SearchResultsState;
import interface_adapter.search_results.SearchResultsViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.List;

public class SearchResultsView extends JPanel implements PropertyChangeListener {

    private final SearchResultsViewModel searchResultsViewModel;

    private final JButton backButton;
    private final JPanel resultsPanel;

    private final JScrollPane resultsScrollPane;

    public SearchResultsView(SearchResultsViewModel searchResultsViewModel) {
        this.searchResultsViewModel = searchResultsViewModel;
        this.searchResultsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Results");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Back");
        backButtonPanel.add(backButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(backButtonPanel);

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        resultsScrollPane = new JScrollPane(resultsPanel);
        resultsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(resultsScrollPane);

    }

    public void populateRecipeList(List<Recipe> recipes) {
        // Clear previous results
        resultsPanel.removeAll();

        for (Recipe recipe : recipes) {

            // Create a panel for each recipe
            final JPanel recipePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            recipePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add some padding


            final JLabel recipeNameLabel = new JLabel(recipe.getName());
            recipeNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            recipePanel.add(recipeNameLabel);

            final JLabel recipeDetailsLabel = new JLabel(
                    " - Calories: " + recipe.getCalories() + ", Servings: " + recipe.getServings()
            );
            recipeDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            recipePanel.add(recipeDetailsLabel);

            // Add the recipe panel to the results panel
            resultsPanel.add(recipePanel);
        }

        // Refresh the results panel to display the new components
        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final SearchResultsState state = (SearchResultsState) evt.getNewValue();

            if (state.getSearchResults() != null) {
                populateRecipeList(state.getSearchResults());
            }
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Search Results");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        // Create ViewModel (dummy in this case)
        SearchResultsViewModel viewModel = new SearchResultsViewModel();

        // Create SearchResultsView
        SearchResultsView searchResultsView = new SearchResultsView(viewModel);

        // Sample recipes
        List<Recipe> recipes = new ArrayList<>();
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
        recipes.add(pasta);
        recipes.add(salad);
        recipes.add(smoothie);
        searchResultsView.populateRecipeList(recipes);

        frame.add(searchResultsView);
        frame.setVisible(true);
    }
}
