package use_case.like_a_recipe;

import entity.Recipe;
import java.io.IOException;
import java.util.List;

/**
 * DAO for the Like a Recipe Case, accessing and updating recipe data with "like" functionality.
 */
public interface LikeRecipeDataAccessInterface {

    /**
     * Retrieves a single recipe by its name.
     *
     * @param recipeName the name of the recipe to retrieve.
     * @return the {@link Recipe} object corresponding to the specified recipe name.
     */
    Recipe getOneRecipe(String recipeName);

    void deleteFileFromFileIo();

    void writeRecipesToFile(List<Recipe> updatedRecipes);

    List<Recipe> getCachedRecipes();

    void uploadFileToFileIo();
}
