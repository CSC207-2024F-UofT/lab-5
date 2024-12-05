package view;

import data_access.NutritionInformationDAO;
import entity.Nutrition;
import interface_adapter.nutrition_information.NutritionInformationPresenter;
import use_case.nutrition_information.NutritionInformationInputData;
import use_case.nutrition_information.NutritionInformationInteractor;

import javax.swing.*;
import java.util.List;

public class NutritionInformationView extends JFrame {
    private final int recipeId;
    private final NutritionInformationInteractor interactor;
    private final JList<String> nutritionList;

    public NutritionInformationView(int recipeId) {
        this.recipeId = recipeId;

        nutritionList = new JList<>();
        setTitle("Nutrition Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 300);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JScrollPane(nutritionList));

        NutritionInformationPresenter outputPresenter = new NutritionInformationPresenter();
        NutritionInformationDAO dataFetcher = new NutritionInformationDAO();
        interactor = new NutritionInformationInteractor(dataFetcher, outputPresenter);

        loadNutritionData();

        setVisible(true);
    }

    private void loadNutritionData() {
        NutritionInformationInputData inputData = new NutritionInformationInputData(recipeId);

        List<Nutrition> nutritionData = interactor.NutritionInformation(inputData);

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
    }
}
