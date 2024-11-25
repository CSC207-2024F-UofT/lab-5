package interface_adapter.saved_recipes;

import interface_adapter.ViewModel;

public class SavedrecipesViewModel extends ViewModel<SavedrecipesState> {

    public SavedrecipesViewModel() {
        super("SavedRecipes");
        setState(new SavedrecipesState());
    }
}
