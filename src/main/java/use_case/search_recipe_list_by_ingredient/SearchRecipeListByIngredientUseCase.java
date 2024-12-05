package use_case.search_recipe_list_by_ingredient;

import entity.Ingredient;
import entity.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchRecipeListByIngredientUseCase {
    private final List<Recipe> recipeList;

    public SearchRecipeListByIngredientUseCase(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public List<Recipe> searchRecipes(List<String> enteredIngredients) {
        final List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            final List<Ingredient> recipeIngredients = recipe.getIngredients();
            final List<String> recipeIngredientsString = new ArrayList<>();
            for (Ingredient ingredient : recipeIngredients) {
                final String[] words = ingredient.getName().split(" ");
                recipeIngredientsString.addAll(Arrays.asList(words));
            }
            for (String recipeIngredient : recipeIngredientsString) {
                for (String enteredIngredient : enteredIngredients) {
                    if (recipeIngredient.equalsIgnoreCase(enteredIngredient)) {
                        results.add(recipe);
                    }
                }
            }
        }
        return results;
    }
}
