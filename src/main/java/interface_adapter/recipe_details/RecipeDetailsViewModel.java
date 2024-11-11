package interface_adapter.recipe_details;

import interface_adapter.ViewModel;


public class RecipeDetailsViewModel extends ViewModel<RecipeDetailsState> {

    public static final String TITLE_LABEL = "Recipe Details View";

    public RecipeDetailsViewModel() {
        super("Recipe Details");
        setState(new RecipeDetailsState());
    }
}
