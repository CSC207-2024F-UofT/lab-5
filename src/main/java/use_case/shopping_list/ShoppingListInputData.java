package use_case.shopping_list;

import java.util.List;

public class ShoppingListInputData {
    private final String[] recipeNames;
    private final String username;

    public ShoppingListInputData(String username, String[] recipeNames) {
        this.recipeNames = recipeNames;
        this.username = username;
    }

    public String[] getRecipeNames() {
        return recipeNames;
    }

    public String getUsername() {
        return username;
    }
}

