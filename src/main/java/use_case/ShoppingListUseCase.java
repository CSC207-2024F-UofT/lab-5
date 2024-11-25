package use_case;

import data_access.SpoonacularAPI;
import java.util.List;

public class ShoppingListUseCase {
    private final SpoonacularAPI api;

    public ShoppingListUseCase(SpoonacularAPI api) {
        this.api = api;
    }

    public List<String> getRecipesContaining(List<String> availableIngredients) {
        return api.getRecipesContaining(availableIngredients);
    }

    public String generateShoppingList(List<String> selectedRecipes, List<String> availableIngredients) {
        List<String> shoppingList = api.getMissingIngredients(selectedRecipes, availableIngredients);
        if (shoppingList.isEmpty()) {
            return "You already have all the ingredients!";
        }
        return String.join("\n", shoppingList);
    }
}
