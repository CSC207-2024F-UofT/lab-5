//package interface_adapter.bookmark;
//
//import interface_adapter.RecipeListController;
//import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientUseCase;
//import use_case.SearchRecipeListByNameUseCase;
//
//public class BookmarkController extends RecipeListController {
//    private final BookmarkInputBoundary bookmarkUseCaseInteractor;
//
//    public BookmarkController(SearchRecipeListByIngredientUseCase searchRecipeListByIngredientUseCase,
//                              SearchRecipeListByNameUseCase searchRecipeListByNameUseCase,
//                              BookmarkInputBoundary bookmarkUseCaseInteractor) {
//        super(searchRecipeListByIngredientUseCase, searchRecipeListByNameUseCase);
//        this.bookmarkUseCaseInteractor = bookmarkUseCaseInteractor;
//    }
//
//    public void execute(...) {
//        final BookmarkInputData bookmarkInputData = new BookmarkInputData(...);
//
//        bookmarkUseCaseInteractor.execute(bookmarkInputData);
//    }
//
////    public addBookmark(Recipe recipe) {
////
////    }
////
////    public void execute(Recipe recipe) {
////        final BookmarkInputData bookmarkInputData = new BookmarkInputData(recipe);
////
////        bookmarkUseCaseInteractor.execute(bookmarkInputData);
//}
