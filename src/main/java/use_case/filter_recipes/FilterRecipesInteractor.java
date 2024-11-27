package use_case.filter_recipes;

import java.util.List;

import data_access.RecipeDAO;
import data_access.SpoonacularRecipeDAO;
import entity.Recipe;

/**
 * The Filter Recipes Interactor.
 */
public class FilterRecipesInteractor implements FilterRecipesInputBoundary {

    private FilterRecipesDataAccessInterface userDataAccessObject;
    private FilterRecipesOutputBoundary outputBoundary;
    private SpoonacularRecipeDAO recipeDataAccessObject;

    // overloading constructor for now
    public FilterRecipesInteractor(SpoonacularRecipeDAO recipeDataAccessObject) {
        this.recipeDataAccessObject = recipeDataAccessObject;
    }

    public FilterRecipesInteractor(FilterRecipesDataAccessInterface userDataAccessObject,
                                   FilterRecipesOutputBoundary outputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.outputBoundary = outputBoundary;
    }

/*    @Override
    public void execute(FilterRecipesInputData filterRecipesInputData) {
        try {
            final String diet = filterRecipesInputData.getDietaryPreference().toString();
            final String cuisine = filterRecipesInputData.getCuisinePreference().toString();
            final List<String> ingredients = filterRecipesInputData.getIngredients();

            final List<Recipe> recipes = userDataAccessObject.filterSearchRecipes(ingredients, diet, cuisine);
            final FilterRecipesOutputData outputData = new FilterRecipesOutputData(recipes);
            outputBoundary.prepareSuccessView(outputData);
        }
        catch (Exception exception) {
            outputBoundary.prepareFailView("Failed to filter recipes: " + exception.getMessage());
        }
    }*/

    @Override
    public List<String> getAvailableDiets() {
        // return userDataAccessObject.getAvailableDiets();
        return recipeDataAccessObject.getAvailableDiets();
    }

    public List<String> getAvailableCuisines() {
        // return userDataAccessObject.getAvailableCuisines();
        return recipeDataAccessObject.getAvailableCuisines();
    }

    /**
     * Filter searches recipe from DAO.
     * @param ingredients list of ingredients
     * @param diet diet choice
     * @param cuisine cuisine choice
     * @return list of recipes
     */
    @Override
    public List<Recipe> filterSearchRecipes(List<String> ingredients, String diet, String cuisine) {
        // return userDataAccessObject.filterSearchRecipes(ingredients, diet, cuisine);
        // final List<Recipe> recipes = userDataAccessObject.filterSearchRecipes(ingredients, diet, cuisine);
        // final FilterRecipesOutputData outputData = new FilterRecipesOutputData(recipes);
        // outputBoundary.prepareSuccessView(outputData);
        // return recipes;
        return recipeDataAccessObject.filterSearchRecipes(ingredients, diet, cuisine);
    }

    /*    @Override
    public void filterRecipesByDiet(DietaryPreference diet) {
        try {
            final List<Recipe> recipes = userDataAccessObject.filterRecipesByDiet(diet);
            final FilterRecipesOutputData outputData = new FilterRecipesOutputData(recipes);
            outputBoundary.prepareSuccessView(outputData);
        }
        catch (Exception exception) {
            outputBoundary.prepareFailView("Failed to filter recipes by diet: " + exception.getMessage());
        }
    }

    @Override
    public void filterRecipesByCuisine(CuisinePreference cuisine) {
        try {
            final List<Recipe> recipes = userDataAccessObject.filterRecipesByCuisine(cuisine);
            final FilterRecipesOutputData outputData = new FilterRecipesOutputData(recipes);
            outputBoundary.prepareSuccessView(outputData);
        }
        catch (Exception exception) {
            outputBoundary.prepareFailView("Failed to filter recipes by cuisine: "
                    + exception.getMessage());
        }
    }*/

}
