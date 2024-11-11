package view;

import entity.Recipe;
import entity.Nutrition;

import javax.swing.*;
import java.awt.*;

public class IndividualRecipeView extends JFrame {
    private JButton homeButton;
    private JButton nutritionButton;
    private JList<String> ingredientsList;
    private Recipe recipe;
    private Nutrition nutrition;

    public IndividualRecipeView(Recipe recipe, Nutrition nutrition) {
        // TODO double check this
        this.recipe = recipe;
        this.ingredientsList = new JList<>(recipe.getIngredients().toArray(new String[0]));
        this.nutrition = nutrition;
        this.nutritionButton = new JButton("Nutrition");

        setTitle(recipe.getName());
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(ingredientsList = new JList<>());
        add(nutritionButton);
        nutritionButton.addActionListener(e -> {
            new NutritionView(recipe);
        });
        setVisible(true);
    }
}
