package interface_adapter.recipe_search;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Recipe Search Use Case.
 */
public class RecipeSearchController {

    public RecipeSearchController() {

    }

    /**
     * Executes the Recipe Search Use Case.
     * @param title the recipe the user wants
     * @param calorieInfo the user's calorie specifications
     * @param carbInfo the user's carbohydrate specifications
     * @param proteinInfo the user's protein specifications
     * @param fatInfo the user's fat specifications
     */
    public void execute(String title, String calorieInfo, String carbInfo, String proteinInfo, String fatInfo) {
        final RecipeSearchInputData recipeSearchInputData = new SignupInputData(
                title, calorieInfo, carbInfo, proteinInfo, fatInfo);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
