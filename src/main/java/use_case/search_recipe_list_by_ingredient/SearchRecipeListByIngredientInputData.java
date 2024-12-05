package use_case.search_recipe_list_by_ingredient;

import java.util.List;

/**
 * The input data for the Change Password Use Case.
 */
public class SearchRecipeListByIngredientInputData {

    private final List<String> ingredients;

    public SearchRecipeListByIngredientInputData(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    List<String> getIngredients() {
        return ingredients;
    }
}
