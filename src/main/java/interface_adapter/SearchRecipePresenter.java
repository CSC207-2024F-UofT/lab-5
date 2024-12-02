package interface_adapter;

import entity.Recipe;
import use_case.search_recipe.SearchRecipeOutputBoundary;
import use_case.search_recipe.SearchRecipeOutputData;

import java.util.List;

public class SearchRecipePresenter implements SearchRecipeOutputBoundary {
    private List<Recipe> recipes;

    @Override
    public void presentRecipes(SearchRecipeOutputData outputData) {
        this.recipes = outputData.getRecipes();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

}
