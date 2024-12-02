package use_case.search_recipe_list_by_ingredient;

import entity.User;

import java.util.List;

/**
 * The input data for the Change Password Use Case.
 */
public class SearchRecipeListByIngredientInputData {

    private final List<String> ingredients;
    private final User user;
    private final String folder;

    public SearchRecipeListByIngredientInputData(List<String> ingredients, User user, String folder) {
        this.ingredients = ingredients;
        this.user = user;
        this.folder = folder;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public User getUser() {
        return user;
    }

    public String getFolder() {
        return folder;
    }
}
