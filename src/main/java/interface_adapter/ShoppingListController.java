package interface_adapter;

import use_case.ShoppingListUseCase;
import java.util.List;

public class ShoppingListController {
    private final ShoppingListUseCase shoppingListUseCase;

    public ShoppingListController(ShoppingListUseCase shoppingListUseCase) {
        this.shoppingListUseCase = shoppingListUseCase;
    }

    // Fetch recipes containing available ingredients
    public List<String> getRecipesContaining(List<String> availableIngredients) {
        return shoppingListUseCase.getRecipesContaining(availableIngredients);
    }

    // Generate a shopping list using selected recipes and available ingredients
    public String generateShoppingList(List<String> selectedRecipes, List<String> availableIngredients) {
        return shoppingListUseCase.generateShoppingList(selectedRecipes, availableIngredients);
    }
}
