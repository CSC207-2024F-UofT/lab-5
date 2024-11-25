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

/**
 * The View for the user's recipe history.
 */

public class RecipeHistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Recipe History";

    public RecipeHistoryView(CommonUser user) {
        final JLabel title = new JLabel(viewName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // iterate through each saved recipe and display it
        final Map<Recipe, Integer> recipes = user.getRecipes();
        for (int i = 0; i <= recipes.size(); i++) {
            final JPanel recipe = new JPanel();
            final JLabel name = new JLabel("Recipe Name");
            final JButton link;
            link = new JButton("Click to view");
            recipe.add(name);
            recipe.add(link);
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
