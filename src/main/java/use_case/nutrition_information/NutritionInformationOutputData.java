package use_case.nutrition_information;

import java.util.List;
import entity.Nutrition;

public class NutritionInformationOutputData {
    private final List<Nutrition> nutritionList;

    public NutritionInformationOutputData(List<Nutrition> nutritionList) {
        this.nutritionList = nutritionList;
    }

    public List<Nutrition> getNutritionList() {
        return nutritionList;
    }
}
