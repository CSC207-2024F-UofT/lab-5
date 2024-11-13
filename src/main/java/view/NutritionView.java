package view;

import entity.Recipe;

import javax.swing.*;

public class NutritionView extends JFrame {
    private Recipe recipe;
    private JList<String> nutritionList;

    NutritionView(Recipe recipe) {
        // TODO initialize nutritionList with Nutrition object
        this.nutritionList = new JList<>();
        add(nutritionList);

        setTitle(recipe.getName());
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
