package view;

import java.util.List;

import javax.swing.*;

import data_access.ViewNutritionDataAccessImpl;
import entity.Nutrition;
import use_case.ViewNutritionDataAccessInterface;

public class NutritionView extends JFrame {

    public NutritionView(int recipeId) {
        final JList<String> nutritionList = new JList<>();
        final ViewNutritionDataAccessInterface dataAccess = new ViewNutritionDataAccessImpl();
        final List<Nutrition> nutritionData = dataAccess.getNutritionDataForRecipe(recipeId);

        final DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Nutrition nutrition : nutritionData) {
            final String displayText = String.format("%s: %.2f %s (%.2f%% of daily needs)",
                    nutrition.getName(),
                    nutrition.getAmount(),
                    nutrition.getUnit(),
                    nutrition.getPercentDailyNeeds());
            listModel.addElement(displayText);
        }

        nutritionList.setModel(listModel);

        add(new JScrollPane(nutritionList));
        // setTitle(recipe.getName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
