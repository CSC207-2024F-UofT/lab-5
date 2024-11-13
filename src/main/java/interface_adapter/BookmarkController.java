/*
In progress; commented out everything for now so it doesn't crash the program.
 */

//package interface_adapter;
//
//import entity.Recipe;
//import use_case.SearchBookmarkUseCase;
//import use_case.SearchRecipeUseCase;
//
//import java.util.List;
//
//public class BookmarkController {
//    // private final SearchBookmarkUseCase searchBookmarkUseCase;
//    private final SearchRecipeUseCase searchRecipeUseCase;
//
//    public BookmarkController(SearchRecipeUseCase searchRecipeUseCase) {
//        this.searchRecipeUseCase = searchRecipeUseCase;
//    }
//
////    public BookmarkController(SearchBookmarkUseCase searchBookmarkUseCase) {
////        this.searchBookmarkUseCase = searchBookmarkUseCase;
////    }
//
//    // TODO this method searches for the bookmarked recipes by ingredients contained
//    public List<Recipe> getBookmarksFromIngredients(List<String> ingredients) {
//        return searchRecipeUseCase.searchRecipes(ingredients);
//    }
//
//    // TODO another method that searches for the bookmarked recipes by the name of recipe
//    public List<Recipe> getBookmarksFromNames(List<String> names) {}
//}
