package view;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import entity.Recipe;
import interface_adapter.saved_recipes.SavedrecipesController;
import interface_adapter.saved_recipes.SavedrecipesState;
import interface_adapter.saved_recipes.SavedrecipesViewModel;

/**
 * Saved Recipes view.
 */
public class SavedrecipesView extends JPanel implements PropertyChangeListener {

    private final String viewName = "SavedRecipes";
    private final SavedrecipesViewModel savedrecipesViewModel;

    private final JLabel username;

    private final JButton backButton;

    private final JPanel recipeListPanel;
    private final JScrollPane scrollPane;
    private SavedrecipesController savedrecipesController;

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
        this.add(backButtonPanel);

        recipeListPanel = new JPanel();
        recipeListPanel.setLayout(new BoxLayout(recipeListPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(recipeListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane);

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
                            final SavedrecipesState currentState = savedrecipesViewModel.getState();

                            savedrecipesController.switchToProfileView(currentState.getUsername());
                        }
                    }
                }
        );
    }

    /**
     * Shows list of all saved recipes.
     * @param recipesWithRatings User's saved recipes.
     */
    public void populateRecipeList(Map<Recipe, Integer> recipesWithRatings) {
        recipeListPanel.removeAll();

        for (Map.Entry<Recipe, Integer> entry : recipesWithRatings.entrySet()) {
            final Recipe recipe = entry.getKey();
            final Integer rating = entry.getValue();

            // Create a panel for each recipe entry
            final JPanel recipePanel = new JPanel();
            recipePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            recipePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

            final JLabel recipeLabel = new JLabel(recipe.getName() + " (Rating: " + rating + ")");
            final JButton linkButton = new JButton("View Recipe");

            // Add an action listener to the link button
            linkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(new java.net.URI(recipe.getUrl()));
                    }
                    catch (java.io.IOException ex) {
                        JOptionPane.showMessageDialog(
                                SavedrecipesView.this,
                                "Unable to open link: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    catch (java.net.URISyntaxException ex) {
                        JOptionPane.showMessageDialog(
                                SavedrecipesView.this,
                                "Invalid URL format: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            });

            recipePanel.add(recipeLabel);
            recipePanel.add(linkButton);

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

    public void setSavedrecipesController(SavedrecipesController savedrecipesController) {
        this.savedrecipesController = savedrecipesController;
    }

}
