package use_case.search_recipe_list_by_name;

/**
 * The Search Recipe List By Name Use Case.
 */
public interface SearchRecipeListByNameInputBoundary {

    /**
     * Execute the Search Recipe List By Name Use Case.
     * @param searchRecipeListByNameInputData the input data for this use case
     */
    void execute(SearchRecipeListByNameInputData searchRecipeListByNameInputData);

}
