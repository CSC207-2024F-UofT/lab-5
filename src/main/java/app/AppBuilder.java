package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import interface_adapter.reviews.My_ReviewsController;
import interface_adapter.reviews.My_ReviewsPresenter;
import interface_adapter.reviews.My_ReviewsViewModel;
import data_access.DBUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.survey1.Survey1ViewModel;
import interface_adapter.watchlists.WatchlistsController;
import interface_adapter.watchlists.WatchlistsPresenter;
import interface_adapter.watchlists.WatchlistsViewModel;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.my_reviews.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.watchlists.WatchlistsInputBoundary;
import use_case.watchlists.WatchlistsInteractor;
import use_case.watchlists.WatchlistsOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final CommonUserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoginView loginView;
    private HomeViewModel homeViewModel;
    private HomeView loggedInView;
    private WatchlistsView watchlistsView;
    private WatchlistsViewModel watchlistsViewModel;
    private Survey1View survey1View;
    private Survey1ViewModel survey1ViewModel;
    private My_ReviewsViewModel my_ReviewsViewModel;
    private My_ReviewsView my_ReviewsView;
    private My_ReviewsDataAccessInterface my_ReviewsDataAccessObject;

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

    public AppBuilder addMyReviewsView() {
        // Step 1: Initialize the ViewModel
        my_ReviewsViewModel = new My_ReviewsViewModel();

        // Step 2: Initialize the View and link it to the ViewModel
        my_ReviewsView = new My_ReviewsView(my_ReviewsViewModel);

        // Step 3: Add the View to the CardPanel with its unique name
        cardPanel.add(my_ReviewsView, my_ReviewsView.getViewName());

        // Step 4: Return the AppBuilder for chaining
        return this;
    }

    public AppBuilder addMy_ReviewsUseCase() {

        //   Create the Presenter and link it to the ViewModel
        final My_ReviewsOutputBoundary my_ReviewsOutputBoundary = new My_ReviewsPresenter(my_ReviewsViewModel, viewManagerModel);

        //  Create the Interactor
        final My_ReviewsInputBoundary my_ReviewsInteractor = new My_ReviewsInteractor(
                my_ReviewsDataAccessObject,
                my_ReviewsOutputBoundary
        );

        // Create the Controller
        final My_ReviewsController myReviewsController = new My_ReviewsController(my_ReviewsInteractor);

        // Create the View and link it to the ViewModel and Controller
        final My_ReviewsView myReviewsView = new My_ReviewsView(my_ReviewsViewModel);
        myReviewsView.setMyReviewsController(myReviewsController);

        // Return
        return this;
    }
    /**
     * Adds the Survey1 View to the application.
     * @return this builder
     */
    public AppBuilder addSurvey1View() {
        survey1ViewModel = new Survey1ViewModel();
        survey1View = new Survey1View(survey1ViewModel);
        cardPanel.add(survey1View, survey1View.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel, homeViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
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

//    /**
//     * Adds the Change Password Use Case to the application.
//     * @return this builder
//     */
//    public AppBuilder addChangePasswordUseCase() {
//        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
//                new ChangePasswordPresenter(homeViewModel);
//
//        final ChangePasswordInputBoundary changePasswordInteractor =
//                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);
//
//        final ChangePasswordController changePasswordController =
//                new ChangePasswordController(changePasswordInteractor);
//        loggedInView.setChangePasswordController(changePasswordController);
//        return this;
//    }

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
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWatchlistsUseCase() {
        final WatchlistsOutputBoundary watchlistsOutputBoundary = new WatchlistsPresenter(viewManagerModel,
                watchlistsViewModel, homeViewModel);
        final WatchlistsInputBoundary watchlistsInteractor = new WatchlistsInteractor(
                userDataAccessObject, watchlistsOutputBoundary, userFactory);

        final WatchlistsController controller = new WatchlistsController(watchlistsInteractor);
        watchlistsView.setWatchlistsController(controller);
        return this;
    }

    /**
     * Adds the Home Use Case to the application.
     * @return this builder
     */
    public AppBuilder addHomeUseCase() {
        final HomeOutputBoundary homeOutputBoundary = new HomePresenter(viewManagerModel,
                watchlistsViewModel, homeViewModel);
        final HomeInputBoundary homeInteractor = new HomeInteractor(
                userDataAccessObject, homeOutputBoundary, userFactory);

        final HomeController controller = new HomeController(homeInteractor);
        loggedInView.setHomeController(controller);
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
