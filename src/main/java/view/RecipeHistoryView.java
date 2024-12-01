package view;

import entity.CommonUser;
import entity.Recipe;
import entity.User;

import java.awt.Component;
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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.recipe_review.RecipeReviewController;
import interface_adapter.recipe_review.RecipeReviewPresenter;
import interface_adapter.recipe_review.RecipeReviewState;
import interface_adapter.recipe_review.RecipeReviewViewModel;
import use_case.review_recipe.RecipeReviewInteractor;
import use_case.review_recipe.RecipeReviewOutputBoundary;

/**
 * The View for the user's recipe history.
 */

public class RecipeHistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Recipe History";

    public RecipeHistoryView(CommonUser user) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        final JLabel title = new JLabel(viewName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        // iterate through each saved recipe and display it
        final Map<Recipe, Integer> recipes = user.getRecipes();
        for (Map.Entry<Recipe, Integer> entry : recipes.entrySet()) {
            final Recipe recipeKey = entry.getKey();

            final JPanel recipePanel = new JPanel();
            recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS));

            final JLabel name = new JLabel(recipeKey.getName());
            final JButton link = new JButton("View Recipe");
            recipePanel.add(name);
            recipePanel.add(link);
        }
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }
}
