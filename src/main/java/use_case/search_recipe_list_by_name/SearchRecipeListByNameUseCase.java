package use_case.search_recipe_list_by_name;

import entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class SearchRecipeListByNameUseCase {
    private final List<Recipe> recipeList;

    public SearchRecipeListByNameUseCase(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public List<Recipe> searchRecipes(String name) {
        final List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            final String[] words = recipe.getName().split(" ");
            for (String word : words) {
                if (word.equalsIgnoreCase(name)) {
                    results.add(recipe);
                }
            }
        }
        return results;
    }
}
