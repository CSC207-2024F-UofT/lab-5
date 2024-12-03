package interface_adapter.recipe_details;

import entity.Recipe;
import interface_adapter.ViewModel;

/**
 * The View Model for the Recipe Details View.
 */
public class RecipeDetailsViewModel extends ViewModel<RecipeDetailsState> {

    public RecipeDetailsViewModel() {
        super("Recipe Details");
        setState(new RecipeDetailsState());
    }
}
