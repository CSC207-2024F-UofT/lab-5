package view;

import entity.Nutrition;
import entity.Recipe;
import use_case.ViewNutritionDataAccessInterface;

import javax.swing.*;
import java.util.List;

public class NutritionView extends JFrame {
    private Recipe recipe;
    private JList<String> nutritionList;
    private ViewNutritionDataAccessInterface dataAccess;

    NutritionView(Recipe recipe) {
        this.recipe = recipe;
        this.nutritionList = new JList<>();
        this.nutritionList = new JList<>();

        // 1. 获取 nutritionData 列表
        List<Nutrition> nutritionData = dataAccess.getNutritionDataForRecipe(recipe.getId());

        // 2. 使用 DefaultListModel 将 Nutrition 数据格式化为字符串
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Nutrition nutrition : nutritionData) {
            String displayText = String.format("%s: %.2f %s (%.2f%% of daily needs)",
                    nutrition.getName(),
                    nutrition.getAmount(),
                    nutrition.getUnit(),
                    nutrition.getPercentDailyNeeds());
            listModel.addElement(displayText);
        }

        // 3. 将 listModel 设置到 nutritionList
        nutritionList.setModel(listModel);

        // 4. 添加 nutritionList 到 GUI 并设置窗口属性
        add(new JScrollPane(nutritionList)); // 使用滚动面板包裹 JList，方便查看更多数据
        setTitle(recipe.getName());
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
