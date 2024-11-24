package use_case.profile;

import entity.Recipe;

import java.util.Map;

public class ProfileOutputData {

    private final Map<Recipe, Integer> recipes;
    private final boolean useCaseFailed;

    public ProfileOutputData(Map<Recipe, Integer> recipes, boolean useCaseFailed) {
        this.recipes = recipes;
        this.useCaseFailed = useCaseFailed;
    }

    public Map<Recipe, Integer> getRecipes() {
        return recipes;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
