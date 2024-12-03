package use_case.search_recipe_list_by_name;

import entity.Recipe;
import entity.User;

import java.util.List;

/**
 * The interface of the DAO for the Search Recipe List By Name Use Case.
 */
public interface SearchRecipeListByNameDataAccessInterface {

    /**
     * Searches recipes by name.
     * @param recipeName the recipe name entered.
     * @param user the user.
     * @param folder the folder to search in.
     * @return search results.
     */
    List<Recipe> searchRecipeListByName(String recipeName, User user, String folder);

    boolean addUser(User user);
}
