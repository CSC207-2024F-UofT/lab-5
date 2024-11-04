package app;

import data_access.RecipeDAOImpl;
import interface_adapter.RecipeController;
import use_case.SearchRecipeUseCase;
import view.RecipeView;

public class Main {
    public static void main(String[] args) {
        RecipeDAOImpl recipeDAO = new RecipeDAOImpl();
        SearchRecipeUseCase searchRecipeUseCase = new SearchRecipeUseCase(recipeDAO);
        RecipeController controller = new RecipeController(searchRecipeUseCase);
        new RecipeView(controller);
    }
}
