package view;

import entity.Recipe;
import interface_adapter.search_results.SearchResultsController;
import interface_adapter.search_results.SearchResultsState;
import interface_adapter.search_results.SearchResultsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchResultsView extends JPanel implements PropertyChangeListener {

    private final SearchResultsViewModel searchResultsViewModel;
    private final String viewName = "Results";

    private final JButton backButton;
    private final JPanel resultsPanel;

    private final JScrollPane resultsScrollPane;

    private SearchResultsController searchResultsController;

    public SearchResultsView(SearchResultsViewModel searchResultsViewModel) {
        this.searchResultsViewModel = searchResultsViewModel;
        this.searchResultsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Results");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Back");
        backButtonPanel.add(backButton);

        backButton.addActionListener(

                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        searchResultsController.switchToRecipeSearchView();
                    }
                }
        );

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

            // Add a separator (line) below each recipe
            final Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
            recipePanel.setBorder(BorderFactory.createCompoundBorder(border, recipePanel.getBorder()));

            final JLabel recipeNameLabel = new JLabel(recipe.getName());
            recipeNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            recipePanel.add(recipeNameLabel);

            final JLabel recipeDetailsLabel = new JLabel(
                    " - Calories: " + recipe.getCalories() + ", Servings: " + recipe.getServings()
            );
            recipeDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            recipePanel.add(recipeDetailsLabel);

            final JButton detailsButton;
            detailsButton = new JButton("Details");
            detailsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    searchResultsController.switchToRecipeDetailsView();
                }
            });
            recipePanel.add(detailsButton);

            // Add a MouseListener to handle clicks
            recipePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle the recipe click event
                    searchResultsController.switchToRecipeDetailsView();
                }
            });

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

    public String getViewName() {
        return viewName;
    }

    public void setSearchResultsController(SearchResultsController searchResultsController) {
        this.searchResultsController = searchResultsController;
    }
}
