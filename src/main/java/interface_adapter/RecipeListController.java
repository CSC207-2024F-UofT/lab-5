package interface_adapter;

import entity.Ingredient;
import entity.Recipe;
import use_case.SearchRecipeListByIngredientUseCase;
import use_case.SearchRecipeListByNameUseCase;

import java.util.List;

public class RecipeListController {
    private final SearchRecipeListByIngredientUseCase searchRecipeListByIngredientUseCase;
    private final SearchRecipeListByNameUseCase searchRecipeListByNameUseCase;

    public RecipeListController(SearchRecipeListByIngredientUseCase searchRecipeListByIngredientUseCase,
                                SearchRecipeListByNameUseCase searchRecipeListByNameUseCase) {
        this.searchRecipeListByIngredientUseCase = searchRecipeListByIngredientUseCase;
        this.searchRecipeListByNameUseCase = searchRecipeListByNameUseCase;
    }

    // getters
    public List<Recipe> getRecipesByIngredients(List<Ingredient> ingredients) {
        return searchRecipeListByIngredientUseCase.searchRecipes(ingredients);
    }

    public List<Recipe> getRecipesByName(String name) {
        return searchRecipeListByNameUseCase.searchRecipes(name);
    }
}
