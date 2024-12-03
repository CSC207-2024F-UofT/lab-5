package use_case.search_recipe_list_by_name;

/**
 * The output boundary for the Search Recipe List by Name Use Case.
 */
public interface SearchRecipeListByNameOutputBoundary {
    /**
     * Prepares the success view for the Search Recipe List by Name Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SearchRecipeListByNameOutputData outputData);

    /**
     * Prepares the failure view for the Search Recipe List by Name Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
