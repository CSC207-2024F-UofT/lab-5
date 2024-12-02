package use_case.search_recipe;

import java.util.List;

public class SearchRecipeInputData {
    private final List<String> ingredients;

    public SearchRecipeInputData(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
