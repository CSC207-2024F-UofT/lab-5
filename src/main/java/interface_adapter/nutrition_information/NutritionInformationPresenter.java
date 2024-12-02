package interface_adapter.nutrition_information;

import entity.Nutrition;
import use_case.nutrition_information.NutritionInformationOutputBoundary;
import use_case.nutrition_information.NutritionInformationOutputData;

import java.util.List;

public class NutritionInformationPresenter implements NutritionInformationOutputBoundary {
    @Override
    public List<Nutrition> presentNutritionInformation(NutritionInformationOutputData outputData) {
        return outputData.getNutritionList();
    }
}
