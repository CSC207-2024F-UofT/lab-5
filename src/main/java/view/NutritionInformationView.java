package view;

import data_access.NutritionInformationDAO;
import entity.Nutrition;
import interface_adapter.nutrition_information.NutritionInformationController;
import interface_adapter.nutrition_information.NutritionInformationPresenter;
import use_case.nutrition_information.NutritionInformationInputData;
import use_case.nutrition_information.NutritionInformationInteractor;

import javax.swing.*;
import java.util.List;

/**
 * Nutrition Information View using NutritionInformationController for data fetching.
 */
public class NutritionInformationView extends JFrame {
    private final int recipeId;
    private final NutritionInformationInteractor interactor;
    private final JList<String> nutritionList;
    private final NutritionInformationController controller;

    /**
     * Constructor for the NutritionInformationView.
     * @param controller The controller responsible for fetching nutrition data.
     * @param recipeId The ID of the recipe to display nutrition information for.
     */
    public NutritionInformationView(int recipeId, NutritionInformationController controller) {
        this.recipeId = recipeId;
        this.controller = controller;

        // Initialize UI components
        nutritionList = new JList<>();
        setTitle("Nutrition Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 300);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JScrollPane(nutritionList));

        final NutritionInformationPresenter outputPresenter = new NutritionInformationPresenter();
        final NutritionInformationDAO dataFetcher = new NutritionInformationDAO();
        interactor = new NutritionInformationInteractor(dataFetcher, outputPresenter);

        // Load nutrition data
        loadNutritionData();

        setVisible(true);
    }

    /**
     * Load nutrition data using the controller and updates the UI.
     */
    private void loadNutritionData() {
        // Fetch nutrition data through the controller
        List<Nutrition> nutritionData = controller.execute((recipeId));

        // Populate the nutrition list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Nutrition nutrition : nutritionData) {
            final String displayText = String.format("%s: %.2f %s (%.2f%% of daily needs)",
                    nutrition.getName(),
                    nutrition.getAmount(),
                    nutrition.getUnit(),
                    nutrition.getPercentDailyNeeds());
            listModel.addElement(displayText);
        }
        nutritionList.setModel(listModel);
    }
}
