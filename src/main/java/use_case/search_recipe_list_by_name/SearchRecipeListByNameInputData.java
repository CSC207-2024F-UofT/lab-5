package use_case.search_recipe_list_by_name;

import entity.User;

/**
 * The input data for the Search Recipe List By Name Use Case.
 */
public class SearchRecipeListByNameInputData {

    private final String recipeName;
    private final User user;
    private final String folder;

    public SearchRecipeListByNameInputData(String recipeName, User user, String folder) {
        this.recipeName = recipeName;
        this.user = user;
        this.folder = folder;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public User getUser() {
        return user;
    }

    public String getFolder() {
        return folder;
    }
}
