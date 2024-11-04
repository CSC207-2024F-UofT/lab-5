package use_case;

import data_access.RecipeDAO;
import entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class SearchRecipeUseCase {
    private final RecipeDAO recipeDAO;

    public SearchRecipeUseCase(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    public List<Recipe> searchRecipes(List<String> ingredients) {
        List<Recipe> allRecipes = recipeDAO.getAllRecipes();
        List<Recipe> filteredRecipes = new ArrayList<>();

        for (Recipe recipe : allRecipes){
            for (String ingredient : ingredients){
                if (recipe.getIngredients().contains(ingredient.trim().toLowerCase())){
                    filteredRecipes.add(recipe);
                    break;
                }
            }
        }
        return filteredRecipes;
    }
}
