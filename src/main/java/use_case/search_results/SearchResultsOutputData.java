package use_case.search_results;

import entity.Recipe;
import entity.User;

/**
 * Output Data for the Search Results Use Case.
 */
public class SearchResultsOutputData {
    private String username;
    private Recipe recipe;

    public SearchResultsOutputData(String username, Recipe recipe) {
        this.username = username;
        this.recipe = recipe;
    }

    public String getUsername() {
        return username;
    }

    public Recipe getRecipe() {
        return recipe;
    }

}
