package view;

import entity.Recipe;
import entity.Nutrition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndividualRecipeView extends JFrame implements ActionListener {
    private JButton homeButton;
    private JButton nutritionButton;
    private JButton bookmarkButton;
    private JList<String> ingredientsList;
    private Recipe recipe;
    private Nutrition nutrition;

    public IndividualRecipeView(Recipe recipe) {
        // TODO double check this
        this.recipe = recipe;
        this.ingredientsList = new JList<>(recipe.getIngredients().toArray(new String[0]));
        // this.nutrition = nutrition; - removed for now
        this.nutritionButton = new JButton("Nutrition");
        this.bookmarkButton = new JButton("Bookmark");

        setTitle(recipe.getName());
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(ingredientsList = new JList<>());
        add(nutritionButton);
        nutritionButton.addActionListener(this);
        bookmarkButton.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == nutritionButton) {
            new NutritionView(recipe);
        }
        if (event.getSource() == bookmarkButton) {
            // TODO actually add the recipe to bookmarks
            JOptionPane.showConfirmDialog(bookmarkButton, "Added to bookmarks");
        }
    }
}
