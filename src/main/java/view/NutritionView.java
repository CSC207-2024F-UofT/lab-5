package view;

import entity.Recipe;

import javax.swing.*;

public class NutritionView extends JFrame {
    private Recipe recipe;
    private JList<String> nutritionList;

    NutritionView(Recipe recipe) {
        this.nutritionList = new JList<>();

        setTitle(recipe.getName());
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(ingredientsList = new JList<>());
        setVisible(true);
    }
}
