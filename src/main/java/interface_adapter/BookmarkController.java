/*
In progress; commented out everything for now so it doesn't crash the program.
 */

package interface_adapter;

import use_case.SearchRecipeListUseCase;

public class BookmarkController extends RecipeListController {

    public BookmarkController(SearchRecipeListUseCase searchRecipeListUseCase) {
        super(searchRecipeListUseCase);
    }

//    public addBookmark(Recipe recipe) {
//
//    }
//
//    public void execute(Recipe recipe) {
//        final BookmarkInputData bookmarkInputData = new BookmarkInputData(recipe);
//
//        bookmarkUseCaseInteractor.execute(bookmarkInputData);
}
