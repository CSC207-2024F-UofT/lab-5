package use_case.search_recipe_list_by_ingredient;

import entity.Recipe;
import entity.User;

import java.util.List;

/**
 * The interface of the DAO for the Search Recipe List By Ingredient Use Case.
 */
public interface SearchRecipeListByIngredientDataAccessInterface {

    /**
     * Searches recipes by ingredient.
     * @param ingredients the ingredients entered.
     * @param user the user.
     * @param folder the folder to search in.
     * @return search results.
     */
    List<Recipe> searchRecipeListByIngredient(List<String> ingredients, User user, String folder);

    boolean addUser(User user);
}
