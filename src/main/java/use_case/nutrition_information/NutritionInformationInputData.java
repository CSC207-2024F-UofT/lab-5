package use_case.nutrition_information;

public class NutritionInformationInputData {
    private final int recipeId;

    public NutritionInformationInputData(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getRecipeId() {
        return recipeId;
    }
}