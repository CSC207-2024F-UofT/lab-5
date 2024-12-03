package interface_adapter.saved_recipes;

import interface_adapter.ViewModel;

/**
 * The View Model for the SavedRecipe View.
 */
public class SavedrecipesViewModel extends ViewModel<SavedrecipesState> {

    public SavedrecipesViewModel() {
        super("SavedRecipes");
        setState(new SavedrecipesState());
    }
}
