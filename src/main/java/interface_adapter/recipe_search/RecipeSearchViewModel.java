package interface_adapter.recipe_search;

import interface_adapter.ViewModel;
import view.RecipeSearchView;

/**
 * The ViewModel for the RecipeSearch View.
 */

public class RecipeSearchViewModel extends ViewModel<RecipeSearchState> {

    public RecipeSearchViewModel() {
        super("recipe search");
        setState(new RecipeSearchState());
    }

    public void addPropertyChangeListener(RecipeSearchView recipeSearchView) {
    }
}
