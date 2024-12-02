package use_case.saved_recipes;

/**
 * Input data for savedrecipesview.
 */
public class SavedRecipeInputData {

    private String username;

    public SavedRecipeInputData(String username) {

        this.username = username;
    }

    /**
     * Get username of the currently logged-in user in saved recipes view.
     * @return username of currently logged-in user in saved recipes view
     */
    public String getUsername() {
        return username;
    }
}
