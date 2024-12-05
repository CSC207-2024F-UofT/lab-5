package app;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DBSearchResultsDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.CommonMovieFactory;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_watchlist.AddToWatchlistController;
import interface_adapter.add_to_watchlist.AddToWatchlistPresenter;
import interface_adapter.create_watchlist.CreateWatchlistController;
import interface_adapter.create_watchlist.CreateWatchlistPresenter;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.home.LoggedInState;
import interface_adapter.leave_review.LeaveReviewController;
import interface_adapter.leave_review.LeaveReviewPresenter;
import interface_adapter.leave_review.LeaveReviewViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.movie.MovieController;
import interface_adapter.movie.MoviePresenter;
import interface_adapter.movie.MovieState;
import interface_adapter.movie.MovieViewModel;
import interface_adapter.my_reviews.MyReviewsController;
import interface_adapter.my_reviews.MyReviewsPresenter;
import interface_adapter.my_reviews.MyReviewsViewModel;
import interface_adapter.search_results.SearchResultsController;
import interface_adapter.search_results.SearchResultsPresenter;
import interface_adapter.search_results.SearchResultsViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.survey_one.SubmitController;
import interface_adapter.survey_one.Survey1Presenter;
import interface_adapter.survey_one.Survey1ViewModel;
import interface_adapter.survey_second_page.SurveySecondPageController;
import interface_adapter.survey_second_page.SurveySecondPagePresenter;
import interface_adapter.survey_second_page.SurveySecondPageViewModel;
import interface_adapter.watchlist.WatchlistController;
import interface_adapter.watchlist.WatchlistPresenter;
import interface_adapter.watchlist.WatchlistViewModel;
import interface_adapter.watchlist.remove.RemoveMovieController;
import interface_adapter.watchlist.remove.RemoveMoviePresenter;
import interface_adapter.watchlists.WatchlistsController;
import interface_adapter.watchlists.WatchlistsPresenter;
import interface_adapter.watchlists.WatchlistsViewModel;
import interface_adapter.watchlists.delete.DeleteWatchlistController;
import interface_adapter.watchlists.delete.DeleteWatchlistPresenter;
import interface_adapter.watchlists.rename.RenameController;
import interface_adapter.watchlists.rename.RenamePresenter;
import use_case.add_to_watchlist.AddToWatchlistInputBoundary;
import use_case.add_to_watchlist.AddToWatchlistInteractor;
import use_case.add_to_watchlist.AddToWatchlistOutputBoundary;
import use_case.create_watchlist.CreateWatchlistInputBoundary;
import use_case.create_watchlist.CreateWatchlistInteractor;
import use_case.create_watchlist.CreateWatchlistOutputBoundary;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import use_case.leave_a_review.LeaveReviewInputBoundary;
import use_case.leave_a_review.LeaveReviewInteractor;
import use_case.leave_a_review.LeaveReviewOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.movie.MovieInputBoundary;
import use_case.movie.MovieInteractor;
import use_case.movie.MovieOutputBoundary;
import use_case.movie.MovieUserDataAccessInterface;
import use_case.my_reviews.*;
import use_case.search_results.SearchResultsInputBoundary;
import use_case.search_results.SearchResultsInteractor;
import use_case.search_results.SearchResultsOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.survey_one.Survey1InputBoundary;
import use_case.survey_one.Survey1Interactor;
import use_case.survey_one.Survey1OutputBoundary;
import use_case.survey_second_page.SurveySecondPageInputBoundary;
import use_case.survey_second_page.SurveySecondPageInteractor;
import use_case.survey_second_page.SurveySecondPageOutputBoundary;
import use_case.watchlist.WatchlistInputBoundary;
import use_case.watchlist.WatchlistInteractor;
import use_case.watchlist.WatchlistOutputBoundary;

import use_case.watchlist.remove.RemoveMovieInputBoundary;
import use_case.watchlist.remove.RemoveMovieInteractor;
import use_case.watchlist.remove.RemoveMovieOutputBoundary;
import use_case.watchlists.WatchlistsInputBoundary;
import use_case.watchlists.WatchlistsInteractor;
import use_case.watchlists.WatchlistsOutputBoundary;
import use_case.watchlists.delete.DeleteWatchlistInputBoundary;
import use_case.watchlists.delete.DeleteWatchlistInteractor;
import use_case.watchlists.delete.DeleteWatchlistOutputBoundary;
import use_case.watchlists.rename.RenameInputBoundary;
import use_case.watchlists.rename.RenameInteractor;
import use_case.watchlists.rename.RenameOutputBoundary;
import view.*;


/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final CommonUserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);


    private final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);
    private final DBSearchResultsDataAccessObject searchResultsDataAccess = new DBSearchResultsDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;

    private LoginViewModel loginViewModel;
    private LoginView loginView;

    private HomeViewModel homeViewModel;
    private HomeView loggedInView;

    private WatchlistsView watchlistsView;
    private WatchlistsViewModel watchlistsViewModel;

    private WatchlistView watchlistView;
    private WatchlistViewModel watchlistViewModel;

    private Survey1View survey1View;
    private Survey1ViewModel survey1ViewModel;

    private MyReviewsViewModel myReviewsViewModel;
    private MyReviewsView myReviewsView;
    private MyReviewsDataAccessInterface myReviewsDataAccessObject;

    private SearchResultsView searchResultsView;
    private SearchResultsViewModel searchResultsViewModel;


    private MovieViewModel movieViewModel;
    private MovieView movieView;

    private SurveySecondPageViewModel surveySecondPageViewModel;
    private SurveySecondPageView surveySecondPageView;
    private LeaveReviewView leaveReviewView;
    private LeaveReviewViewModel leaveReviewViewModel;
    private final LoggedInState loggedInState = new LoggedInState();

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        homeViewModel = new HomeViewModel();
        loggedInView = new HomeView(homeViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the Watchlists View to the application.
     * @return this builder
     */
    public AppBuilder addWatchlistsView() {
        watchlistsViewModel = new WatchlistsViewModel();
        watchlistsView = new WatchlistsView(watchlistsViewModel);
        cardPanel.add(watchlistsView, watchlistsView.getViewName());
        return this;
    }

    /**
     * Adds the Watchlists View to the application.
     * @return this builder
     */
    public AppBuilder addWatchlistView() {
        watchlistViewModel = new WatchlistViewModel();
        watchlistView = new WatchlistView(watchlistViewModel);
        cardPanel.add(watchlistView, watchlistView.getViewName());
        return this;
    }

    /**
     * Adds the MyReviews View to the application.
     * @return this builder
     */
    public AppBuilder addMyReviewsView() {
        // Step 1: Initialize the ViewModel
        myReviewsViewModel = new MyReviewsViewModel();

        // Step 2: Initialize the View and link it to the ViewModel
        myReviewsView = new MyReviewsView(myReviewsViewModel);

        // Step 3: Add the View to the CardPanel with its unique name
        cardPanel.add(myReviewsView, myReviewsView.getViewName());

        // Step 4: Return the AppBuilder for chaining
        return this;
    }

    /**
     * Adds the My_Reviews Use Case to the application.
     * @return this builder
     */
    public AppBuilder addMyReviewsUseCase() {

        //   Create the Presenter and link it to the ViewModel
        final MyReviewsOutputBoundary myReviewsOutputBoundary =
                new MyReviewsPresenter(myReviewsViewModel, viewManagerModel);

        //  Create the Interactor
        final MyReviewsInputBoundary myReviewsInteractor = new MyReviewsInteractor(
                userDataAccessObject, myReviewsOutputBoundary);

        // Create the Controller
        final MyReviewsController myReviewsController = new MyReviewsController(myReviewsInteractor);

        loggedInView.setMyReviewsController(myReviewsController);
        myReviewsView.setMyReviewsController(myReviewsController);
        // Return
        return this;
    }

    /**
     * Adds the Survey1 View to the application.
     * @return this builder
     * @throws IOException checkstyle
     */
    public AppBuilder addSurvey1View() throws IOException {
        survey1ViewModel = new Survey1ViewModel();
        survey1View = new Survey1View(survey1ViewModel);
        cardPanel.add(survey1View, survey1View.getViewName());
        return this;
    }

    /**
     * Adds the Survey1 View to the application.
     * @return this builder
     * @throws IOException checkstyle
     */
    public AppBuilder addSurveySecondPageView() throws IOException {
        surveySecondPageViewModel = new SurveySecondPageViewModel();
        surveySecondPageView = new SurveySecondPageView(surveySecondPageViewModel);
        cardPanel.add(surveySecondPageView, surveySecondPageView.getViewName());
        return this;
    }

    /**
     * Adds the Movie View to the application.
     * @return this builder
     * @throws IOException checkstyle
     */
    public AppBuilder addMovieView() {
        movieViewModel = new MovieViewModel();
        movieView = new MovieView(movieViewModel);
        cardPanel.add(movieView, movieView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel, homeViewModel, survey1ViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the leave review view.
     * @return this builder
     */

    public AppBuilder addLeaveReviewView() {

        leaveReviewViewModel = new LeaveReviewViewModel();

        leaveReviewView = new LeaveReviewView(leaveReviewViewModel);

        cardPanel.add(leaveReviewView, leaveReviewView.getViewName());

        return this;
    }

    /**
     * Adds the leave review use case.
     * @return this builder
     */

    public AppBuilder addLeaveReviewUseCase() {

        final LeaveReviewOutputBoundary leaveReviewPresenter =
                new LeaveReviewPresenter(leaveReviewViewModel, viewManagerModel);

        final LeaveReviewInputBoundary leaveReviewInteractor = new LeaveReviewInteractor(userDataAccessObject,
                leaveReviewPresenter);

        final LeaveReviewController leaveReviewController = new LeaveReviewController(leaveReviewInteractor);

        movieView.setLeaveReviewController(leaveReviewController);
        leaveReviewView.setLeaveReviewController(leaveReviewController);

        return this;

    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                homeViewModel, loginViewModel, signupViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                homeViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Watchlists Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWatchlistsUseCase() {
        final WatchlistsOutputBoundary watchlistsOutputBoundary = new WatchlistsPresenter(viewManagerModel,
                watchlistsViewModel, homeViewModel, watchlistViewModel);
        final WatchlistsInputBoundary watchlistsInteractor = new WatchlistsInteractor(
                userDataAccessObject, watchlistsOutputBoundary, userFactory);

        final WatchlistsController controller = new WatchlistsController(watchlistsInteractor);
        watchlistsView.setWatchlistsController(controller);
        return this;
    }

    /**
     * Adds the Watchlist Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWatchlistUseCase() {
        final WatchlistOutputBoundary watchlistOutputBoundary = new WatchlistPresenter(viewManagerModel,
                watchlistsViewModel, homeViewModel, watchlistViewModel, searchResultsViewModel, movieViewModel);
        final WatchlistInputBoundary watchlistInteractor = new WatchlistInteractor(
                userDataAccessObject, watchlistOutputBoundary);

        final WatchlistController controller = new WatchlistController(watchlistInteractor);
        watchlistView.setWatchlistController(controller);
        return this;
    }

    /**
     * Adds the Home Use Case to the application.
     * @return this builder
     */
    public AppBuilder addHomeUseCase() {
        final HomeOutputBoundary homeOutputBoundary = new HomePresenter(viewManagerModel, watchlistsViewModel, homeViewModel, searchResultsViewModel);

        final HomeInputBoundary homeInteractor = new HomeInteractor(
                userDataAccessObject, homeOutputBoundary, userFactory);

        final HomeController controller = new HomeController(homeInteractor);
        loggedInView.setHomeController(controller);
        return this;
    }

    /**
     * Adds the Search Results View to the application.
     *
     * @return this builder
     */
    public AppBuilder addSearchResultsView() {
        searchResultsViewModel = new SearchResultsViewModel();
        searchResultsView = new SearchResultsView(searchResultsViewModel);
        cardPanel.add(searchResultsView, searchResultsViewModel.getViewName());
        return this;
    }

    /**
    * Adds the Survey1 Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSurvey1UseCase() {
        final Survey1OutputBoundary survey1OutputBoundary = new Survey1Presenter(
                survey1ViewModel, surveySecondPageViewModel, viewManagerModel);
        final Survey1InputBoundary survey1Interactor = new Survey1Interactor(
                userDataAccessObject, survey1OutputBoundary, userFactory);

        final SubmitController controller = new SubmitController(survey1Interactor);
        survey1View.setSubmitController(controller);
        return this;
    }

    /**
     * Adds the Search Results Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addSearchResultsUseCase() {
        final SearchResultsOutputBoundary searchResultsOutputBoundary = new SearchResultsPresenter(viewManagerModel,
                searchResultsViewModel, homeViewModel, movieViewModel);
        final SearchResultsInputBoundary searchResultsInteractor = new SearchResultsInteractor(searchResultsDataAccess,
                searchResultsOutputBoundary, userDataAccessObject);
        final SearchResultsController controller = new SearchResultsController(searchResultsInteractor);
        searchResultsView.setSearchResultsController(controller);
        return this;
    }

    /**
     * Adds the Survey Second Page Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSurveySecondPageUseCase() {
        final SurveySecondPageOutputBoundary surveySecondPageOutputBoundary = new SurveySecondPagePresenter(
                viewManagerModel, surveySecondPageViewModel, homeViewModel);
        final SurveySecondPageInputBoundary surveySecondPageInteractor = new SurveySecondPageInteractor(
                userDataAccessObject, surveySecondPageOutputBoundary, userFactory);

        final SurveySecondPageController controller = new SurveySecondPageController(surveySecondPageInteractor);
        surveySecondPageView.setSurveySecondPageController(controller);

        return this;
    }

    /**
     * Adds the Movie Use Case to the application.
     * @return this builder
     */
    public AppBuilder addMovieUseCase() {
        final MovieOutputBoundary movieOutputBoundary = new MoviePresenter(viewManagerModel,
                movieViewModel, homeViewModel, leaveReviewViewModel);
        final MovieInputBoundary movieInteractor = new MovieInteractor(movieOutputBoundary);
        final MovieController controller = new MovieController(movieInteractor);
        movieView.setMovieController(controller);
        return this;
    }

    /**
     * Adds the Create Watchlist Use Case to the application.
     * @return this builder
     */
    public AppBuilder addCreateWatchlistUseCase() {
        final CreateWatchlistOutputBoundary createWatchlistOutputBoundary = new CreateWatchlistPresenter(
                viewManagerModel, watchlistsViewModel);
        final CreateWatchlistInputBoundary createWatchlistInteractor = new CreateWatchlistInteractor(
                userDataAccessObject, createWatchlistOutputBoundary);

        final CreateWatchlistController controller = new CreateWatchlistController(createWatchlistInteractor);
        watchlistsView.setCreateWatchlistController(controller);
        return this;
    }

    /**
     * Adds the Create Watchlist Use Case to the application.
     * @return this builder
     */
    public AppBuilder addRenameUseCase() {
        final RenameOutputBoundary renameOutputBoundary = new RenamePresenter(
                viewManagerModel, watchlistsViewModel);
        final RenameInputBoundary renameInteractor = new RenameInteractor(
                userDataAccessObject, renameOutputBoundary);

        final RenameController controller = new RenameController(renameInteractor);
        watchlistsView.setRenameController(controller);
        return this;
    }

    /**
     * Adds the Delete Watchlist Use Case to the application.
     * @return this builder
     */
    public AppBuilder addDeleteUseCase() {
        final DeleteWatchlistOutputBoundary deleteOutputBoundary = new DeleteWatchlistPresenter(
                viewManagerModel, watchlistsViewModel);
        final DeleteWatchlistInputBoundary deleteInteractor = new DeleteWatchlistInteractor(
                userDataAccessObject, deleteOutputBoundary);

        final DeleteWatchlistController controller = new DeleteWatchlistController(deleteInteractor);
        watchlistsView.setDeleteController(controller);
        return this;
    }

    /**
     * Adds the Add to Watchlist Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAddToWatchlistUseCase() {
        final AddToWatchlistOutputBoundary addToWatchlistOutputBoundary = new AddToWatchlistPresenter(
                viewManagerModel, movieViewModel);
        final AddToWatchlistInputBoundary addToWatchlistInteractor = new AddToWatchlistInteractor(
                userDataAccessObject, addToWatchlistOutputBoundary);

        final AddToWatchlistController controller = new AddToWatchlistController(addToWatchlistInteractor);
        movieView.setAddToWatchlistController(controller);
        return this;
    }

    /**
     * Adds the Create Watchlist Use Case to the application.
     * @return this builder
     */
    public AppBuilder addRemoveUseCase() {
        final RemoveMovieOutputBoundary removeOutputBoundary = new RemoveMoviePresenter(
                viewManagerModel, watchlistViewModel);
        final RemoveMovieInputBoundary removeInteractor = new RemoveMovieInteractor(
                userDataAccessObject, removeOutputBoundary);

        final RemoveMovieController controller = new RemoveMovieController(removeInteractor);
        watchlistView.setRemoveMovieController(controller);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Movies4U");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(loginView.getViewName());

        viewManagerModel.firePropertyChanged();

        return application;
    }
}

