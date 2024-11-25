package use_case;

import entity.Ingredient;
import entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class SearchRecipeListByIngredientUseCase {
    private final List<Recipe> recipeList;

    public SearchRecipeListByIngredientUseCase(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public List<Recipe> searchRecipes(List<Ingredient> ingredients) {
        final List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            final List<Ingredient> targetIngredients = recipe.getIngredients();
            for (Ingredient ingredient : ingredients) {
                if (targetIngredients.contains(ingredient)) {
                    results.add(recipe);
                }
            }
        }
        return results;
    }
}
