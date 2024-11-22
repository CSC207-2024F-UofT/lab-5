package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.recipe_search.RecipeSearchViewModel;

/**
 * The View for when the user is searching for a recipe.
 */

public class RecipeSearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Recipe Search";
    private final RecipeSearchViewModel recipeSearchViewModel;

    private final JTextField recipeInputField = new JTextField(15);
    private final JLabel recipeErrorField = new JLabel();

    private final JLabel calories = new JLabel("Calories:");
    private final JTextField caloriesMaxInputField = new JTextField(15);
    private final JTextField caloriesMinInputField = new JTextField(15);

    private final JLabel carbs = new JLabel("Carbohydrates:");
    private final JTextField carbsMaxInputField = new JTextField(15);
    private final JTextField carbsMinInputField = new JTextField(15);

    private final JLabel protein = new JLabel("Protein:");
    private final JTextField proteinMinInputField = new JTextField(15);
    private final JTextField proteinMaxInputField = new JTextField(15);

    private final JLabel fat = new JLabel("Fat:");
    private final JTextField fatMaxInputField = new JTextField(15);
    private final JTextField fatMinInputField = new JTextField(15);

    private final JButton search;
    private final JButton cancel;

    public RecipeSearchView(RecipeSearchViewModel recipeSearchViewModel) {
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.recipeSearchViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(viewName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel recipeInfo = new LabelTextPanel(
                new JLabel("Recipe Name:"), recipeInputField);

        // panel for calorie information
        final JPanel calorieInfo = new JPanel();
        calorieInfo.add(calories);
        calorieInfo.add(caloriesMinInputField);
        calorieInfo.add(caloriesMaxInputField);

        // panel for carbohydrate information
        final JPanel carbInfo = new JPanel();
        carbInfo.add(carbs);
        carbInfo.add(carbsMinInputField);
        carbInfo.add(carbsMaxInputField);

        // panel for protein information
        final JPanel proteinInfo = new JPanel();
        proteinInfo.add(protein);
        proteinInfo.add(proteinMinInputField);
        proteinInfo.add(proteinMaxInputField);

        // panel for fat information
        final JPanel fatInfo = new JPanel();
        fatInfo.add(fat);
        fatInfo.add(fatMinInputField);
        fatInfo.add(fatMaxInputField);

        // panel for buttons
        final JPanel buttons = new JPanel();
        search = new JButton("Search");
        buttons.add(search);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        this.add(title);
        this.add(recipeInfo);
        this.add(calorieInfo);
        this.add(carbInfo);
        this.add(proteinInfo);
        this.add(fatInfo);
        this.add(buttons);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
}
