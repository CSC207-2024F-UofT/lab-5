package use_case.filter_recipes;

/**
 * The output boundary for filter recipes use case.
 */
public interface FilterRecipesOutputBoundary {

    /**
     * Prepare the success view for filter recipes use case.
     * @param outputData the output data
     */
    void prepareSuccessView(FilterRecipesOutputData outputData);

    /**
     * Prepare the failure view for filter recipes use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
