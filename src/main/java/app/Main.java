package app;

import data_access.SpoonacularRecipeDAO;
import interface_adapter.RecipeController;
import use_case.SearchRecipeUseCase;
import view.RecipeView;

public class Main {
    public static void main(String[] args) {
        // Initialize dependencies
        SpoonacularRecipeDAO recipeDAO = new SpoonacularRecipeDAO();
        SearchRecipeUseCase searchRecipeUseCase = new SearchRecipeUseCase(recipeDAO);
        RecipeController controller = new RecipeController(searchRecipeUseCase);

        // Launch the UI
        new RecipeView(controller);
    }
}


