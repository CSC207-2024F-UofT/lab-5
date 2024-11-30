package use_case.saved_recipes;

import entity.Recipe;

import java.util.Map;

public class SavedRecipeOutputData {

    private final String username;
    private final boolean useCaseFailed;

    public SavedRecipeOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
