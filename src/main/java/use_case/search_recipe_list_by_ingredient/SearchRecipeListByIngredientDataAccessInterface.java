package use_case.search_recipe_list_by_ingredient;

import entity.User;

import java.util.List;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface SearchRecipeListByIngredientDataAccessInterface {

    /**
     * Searches recipes by ingredient.
     * @param ingredients the ingredients entered.
     */
    void searchRecipeListByIngredient(List<String> ingredients);
}
