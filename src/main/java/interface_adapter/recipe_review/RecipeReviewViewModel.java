package interface_adapter.recipe_review;


import interface_adapter.ViewModel;
import interface_adapter.recipe_search.RecipeSearchState;
import view.RecipeHistoryView;

import java.beans.PropertyChangeListener;

public class RecipeReviewViewModel extends ViewModel<RecipeReviewState> {
    public RecipeReviewViewModel() {
        super("recipe review");
        setState(new RecipeReviewState());
    }

    @Override
    public void addPropertyChangeListener(RecipeHistoryView recipeHistoryView) {
        super.addPropertyChangeListener(recipeHistoryView);
    }
}
