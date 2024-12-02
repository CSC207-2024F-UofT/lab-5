package use_case.profile;

import java.util.Map;

import entity.Recipe;

/**
 * Output data of profile view.
 */
public class ProfileOutputData {

    private final Map<Recipe, Integer> recipes;
    private final String username;
    private final boolean useCaseFailed;

    public ProfileOutputData(String username, Map<Recipe, Integer> recipes, boolean useCaseFailed) {
        this.username = username;
        this.recipes = recipes;
        this.useCaseFailed = useCaseFailed;
    }

    public Map<Recipe, Integer> getRecipes() {
        return recipes;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
