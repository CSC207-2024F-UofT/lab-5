package interface_adapter.recipe_details;

import interface_adapter.ViewModel;

/**
 * The View Model for the Recipe Details View.
 */
public class RecipeDetailsViewModel extends ViewModel<RecipeDetailsState> {

    public static final String TITLE_LABEL = "Recipe Details View";
    private String recipeName;
    private int calories;
    private int servings;
    private String recipeLink;
    private String[] nutrients;
    private int[] nutrientValues;

    public RecipeDetailsViewModel() {
        super("Recipe Details");
        setState(new RecipeDetailsState());
    }
}
