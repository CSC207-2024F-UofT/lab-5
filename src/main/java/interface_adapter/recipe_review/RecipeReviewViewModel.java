package interface_adapter.recipe_review;

import interface_adapter.ViewModel;

public class RecipeReviewViewModel extends ViewModel<RecipeReviewState> {
    public RecipeReviewViewModel() {
        super("recipe review");
        setState(new RecipeReviewState());
    }

    public RecipeReviewState getState() {
        return (RecipeReviewState) super.getState();
    }

    public void setState(RecipeReviewState state) {
        super.setState(state);
    }
}
