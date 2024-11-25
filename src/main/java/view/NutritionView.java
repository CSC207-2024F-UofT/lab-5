package view;

import data_access.ViewNutritionDataAccessImpl;
import entity.Nutrition;
import use_case.ViewNutritionDataAccessInterface;

import javax.swing.*;
import java.util.List;

public class NutritionView extends JFrame {
    final private int recipeId;
    private JList<String> nutritionList;
    private ViewNutritionDataAccessInterface dataAccess;

    public NutritionView(int recipeId) {
        this.recipeId = recipeId;
        this.nutritionList = new JList<>();
        this.dataAccess = new ViewNutritionDataAccessImpl();

        List<Nutrition> nutritionData = dataAccess.getNutritionDataForRecipe(recipeId);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Nutrition nutrition : nutritionData) {
            String displayText = String.format("%s: %.2f %s (%.2f%% of daily needs)",
                    nutrition.getName(),
                    nutrition.getAmount(),
                    nutrition.getUnit(),
                    nutrition.getPercentDailyNeeds());
            listModel.addElement(displayText);
        }

        nutritionList.setModel(listModel);

        add(new JScrollPane(nutritionList));
        // setTitle(recipe.getName());
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
