package interface_adapter;

import entity.Recipe;
import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientUseCase;
import use_case.search_recipe_list_by_name.SearchRecipeListByNameUseCase;

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
    public List<Recipe> getRecipesByIngredients(List<String> ingredients) {
        return searchRecipeListByIngredientUseCase.searchRecipes(ingredients);
    }

    public List<Recipe> getRecipesByName(String name) {
        return searchRecipeListByNameUseCase.searchRecipes(name);
    }
}
