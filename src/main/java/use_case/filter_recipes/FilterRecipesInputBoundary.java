package use_case.filter_recipes;

/**
 * The filter recipes input boundary interface for calling the use case.
 */
public interface FilterRecipesInputBoundary {

    /**
     * Execute the filter recipes use case.
     * @param filterRecipesInputData the input data for this use case
     */
    void execute(FilterRecipesInputData filterRecipesInputData);

}
