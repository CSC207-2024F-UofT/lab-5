//package app;
//
//import data_access.UserDAOImpl;
//import interface_adapter.RecipeListViewModel;
//import interface_adapter.ViewManagerModel;
//import interface_adapter.search_recipe_list_by_ingredient.SearchRecipeListByIngredientController;
//import interface_adapter.search_recipe_list_by_ingredient.SearchRecipeListByIngredientPresenter;
//import interface_adapter.search_recipe_list_by_name.SearchRecipeListByNameController;
//import interface_adapter.search_recipe_list_by_name.SearchRecipeListByNamePresenter;
//import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientInputBoundary;
//import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientInteractor;
//import use_case.search_recipe_list_by_ingredient.SearchRecipeListByIngredientOutputBoundary;
//import use_case.search_recipe_list_by_name.SearchRecipeListByNameInputBoundary;
//import use_case.search_recipe_list_by_name.SearchRecipeListByNameInteractor;
//import use_case.search_recipe_list_by_name.SearchRecipeListByNameOutputBoundary;
//import view.*;
//
//import java.awt.CardLayout;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.WindowConstants;
//
///**
// * The AppBuilder class is responsible for putting together the pieces of
// * our CA architecture; piece by piece.
// * <p/>
// * This is done by adding each View and then adding related Use Cases.
// */
//public class AppBuilder {
//    private final JPanel cardPanel = new JPanel();
//    private final CardLayout cardLayout = new CardLayout();
//    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
//    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
//
//    private final UserDAOImpl userDataAccessObject = new UserDAOImpl();
//
//    private BookmarkView bookmarkView;
//    private FolderView folderView;
//    private HomePage homepage;
//    private IndividualRecipeView individualRecipeView;
//    private LoginSignupPage loginSignupPage;
//    private NutritionInformationView nutritionInformationView;
//    private RecentlyViewedView recentlyViewedView;
//    private RecipeListView recipeListView;
//    private RecipeView recipeView;
//    private ShoppingListGUI shoppingListGUI;
//    private RecipeListViewModel recipeListViewModel;
//
//    public AppBuilder() {
//        cardPanel.setLayout(cardLayout);
//    }
//
//    /**
//     * Adds the Bookmark View to the application.
//     * @return this builder
//     */
//    public AppBuilder addBookmarkView() {
//        recipeListViewModel = new RecipeListViewModel();
//        // TODO figure out how to remove user
//        bookmarkView = new BookmarkView(user, "bookmarks", recipeListViewModel);
//        cardPanel.add(bookmarkView);
//        return this;
//    }
//
////    /**
////     * Adds the Login View to the application.
////     * @return this builder
////     */
////    public AppBuilder addLoginView() {
////        loginViewModel = new LoginViewModel();
////        loginView = new LoginView(loginViewModel);
////        cardPanel.add(loginView, loginView.getViewName());
////        return this;
////    }
////
////    /**
////     * Adds the LoggedIn View to the application.
////     * @return this builder
////     */
////    public AppBuilder addLoggedInView() {
////        loggedInViewModel = new LoggedInViewModel();
////        loggedInView = new LoggedInView(loggedInViewModel);
////        cardPanel.add(loggedInView, loggedInView.getViewName());
////        return this;
////    }
//
//    /**
//     * Adds the Signup Use Case to the application.
//     * @return this builder
//     */
//    public AppBuilder addSearchRecipeListByIngredientUseCase() {
//        final SearchRecipeListByIngredientOutputBoundary searchRecipeListByIngredientOutputBoundary =
//                new SearchRecipeListByIngredientPresenter(recipeListViewModel);
//        final SearchRecipeListByIngredientInputBoundary searchRecipeListByIngredientInteractor =
//                new SearchRecipeListByIngredientInteractor(
//                userDataAccessObject, searchRecipeListByIngredientOutputBoundary);
//
//        final SearchRecipeListByIngredientController controller =
//                new SearchRecipeListByIngredientController(searchRecipeListByIngredientInteractor);
//        recipeListView.setSearchRecipeListByIngredientController(controller);
//        return this;
//    }
//
//    /**
//     * Adds the Login Use Case to the application.
//     * @return this builder
//     */
//    public AppBuilder addSearchRecipeListByNameUseCase() {
//        final SearchRecipeListByNameOutputBoundary searchRecipeListByNameOutputBoundary =
//                new SearchRecipeListByNamePresenter(recipeListViewModel);
//        final SearchRecipeListByNameInputBoundary searchRecipeListByNameInteractor =
//                new SearchRecipeListByNameInteractor(
//                        userDataAccessObject, searchRecipeListByNameOutputBoundary);
//
//        final SearchRecipeListByNameController controller =
//                new SearchRecipeListByNameController(searchRecipeListByNameInteractor);
//        recipeListView.setSearchRecipeListByNameController(controller);
//        return this;
//    }
//
////    /**
////     * Adds the Change Password Use Case to the application.
////     * @return this builder
////     */
////    public AppBuilder addChangePasswordUseCase() {
////        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
////                new ChangePasswordPresenter(loggedInViewModel);
////
////        final ChangePasswordInputBoundary changePasswordInteractor =
////                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);
////
////        final ChangePasswordController changePasswordController =
////                new ChangePasswordController(changePasswordInteractor);
////        loggedInView.setChangePasswordController(changePasswordController);
////        return this;
////    }
////
////    /**
////     * Adds the Logout Use Case to the application.
////     * @return this builder
////     */
////    public AppBuilder addLogoutUseCase() {
////        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
////                loggedInViewModel, loginViewModel);
////
////        final LogoutInputBoundary logoutInteractor =
////                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);
////
////        final LogoutController logoutController = new LogoutController(logoutInteractor);
////        loggedInView.setLogoutController(logoutController);
////        return this;
////    }
//
//    /**
//     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
//     * @return the application
//     */
//    public JFrame build() {
//        final JFrame application = new JFrame("Recipe Generator");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        application.add(cardPanel);
//
//        viewManagerModel.setState(homepage.getName());
//        viewManagerModel.firePropertyChanged();
//
//        return application;
//    }
//}
