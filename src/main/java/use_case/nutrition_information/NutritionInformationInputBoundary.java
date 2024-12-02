package use_case.nutrition_information;

import entity.Nutrition;

import java.util.List;

public interface NutritionInformationInputBoundary {
    List<Nutrition> NutritionInformation(NutritionInformationInputData inputData);
}
