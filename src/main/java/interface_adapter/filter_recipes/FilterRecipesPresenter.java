package interface_adapter.filter_recipes;

import use_case.filter_recipes.FilterRecipesOutputBoundary;
import use_case.filter_recipes.FilterRecipesOutputData;

/**
 * Presenter for the filter recipes use case.
 */
public class FilterRecipesPresenter implements FilterRecipesOutputBoundary {
    private final FilterRecipesOutputBoundary filterRecipesOutputBoundary;

    public FilterRecipesPresenter(FilterRecipesOutputBoundary filterRecipesOutputBoundary) {
        this.filterRecipesOutputBoundary = filterRecipesOutputBoundary;
    }

    @Override
    public void prepareSuccessView(FilterRecipesOutputData filterRecipesOutputData) {
        filterRecipesOutputBoundary.prepareSuccessView(filterRecipesOutputData);
    }

    @Override
    public void prepareFailView(String error) {
        filterRecipesOutputBoundary.prepareFailView(error);
    }

}
