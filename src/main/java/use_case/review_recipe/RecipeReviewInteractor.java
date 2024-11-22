package use_case.review_recipe;

/**
 * The Recipe Review Interactor.
 */
public class RecipeReviewInteractor {
    private RecipeReviewOutputBoundary userPresenter;
    public void execute() {

    }

    public void switchToRecipeHistoryViewView() {
        userPresenter.switchToRecipeHistoryView();
    }
}
