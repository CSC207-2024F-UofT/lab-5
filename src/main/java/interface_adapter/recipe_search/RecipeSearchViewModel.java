package interface_adapter.recipe_search;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Recipe Search View.
 */
public class RecipeSearchViewModel extends ViewModel<RecipeSearchState> {

    public RecipeSearchViewModel() {
        super("Recipe Search");
        setState(new RecipeSearchState());
    }
}
