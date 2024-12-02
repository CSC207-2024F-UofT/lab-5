//package interface_adapter.search_recipe_list_by_ingredient;
//
//import interface_adapter.RecipeListViewModel;
//import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientOutputBoundary;
//import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientOutputData;
//
///**
// * The Presenter for the Search Recipe List By Ingredients Use Case.
// */
//public class SearchRecipeListByIngredientPresenter implements SearchRecipeListByIngredientOutputBoundary {
//
//    private final RecipeListViewModel recipeListViewModel;
//
//    public SearchRecipeListByIngredientPresenter(RecipeListViewModel recipeListViewModel) {
//        this.recipeListViewModel = recipeListViewModel;
//    }
//
//    @Override
//    public void prepareSuccessView(SearchRecipeListByIngredientOutputData outputData) {
//        // TODO
//        recipeListViewModel.firePropertyChanged("???");
//    }
//
//    @Override
//    public void prepareFailView(String error) {
//        // note: this use case currently can't fail
//    }
//}
