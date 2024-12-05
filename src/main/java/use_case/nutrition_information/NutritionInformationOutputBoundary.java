package use_case.nutrition_information;

import entity.Nutrition;

import java.util.List;

public interface NutritionInformationOutputBoundary {
    List<Nutrition> presentNutritionInformation(NutritionInformationOutputData outputData);
}
