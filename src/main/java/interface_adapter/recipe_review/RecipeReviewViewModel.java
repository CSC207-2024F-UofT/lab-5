package interface_adapter.recipe_review;

import interface_adapter.ViewModel;

/**
 * The View Model for the Saved recipes View.
 */
public class RecipeReviewViewModel extends ViewModel<RecipeReviewState> {
    public RecipeReviewViewModel() {
        super("recipe review");
        setState(new RecipeReviewState());
    }
}
