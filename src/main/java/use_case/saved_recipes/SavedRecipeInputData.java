package use_case.saved_recipes;

import entity.Recipe;

import java.util.HashMap;
import java.util.Map;

public class SavedRecipeInputData {

    private String username;

    public SavedRecipeInputData(String username) {

        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}
