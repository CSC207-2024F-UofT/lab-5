package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryUserDataAccessObject;
import entity.BookmarkFactory;
import entity.CommonUserFactory;
import entity.TranslateFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.bookmark.BookmarkController;
import interface_adapter.bookmark.BookmarkPresenter;
import interface_adapter.bookmark.BookmarkViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.bookmark.BookmarkInputBoundary;
import use_case.bookmark.BookmarkInteractor;
import use_case.bookmark.BookmarkOutputBoundary;
import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslatePresenter;
import interface_adapter.translate.TranslateViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInteractor;
import use_case.history.HistoryOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.translator.TranslatorInputBoundary;
import use_case.translator.TranslatorInteractor;
import use_case.translator.TranslatorOutputBoundary;
import view.HistoryView;
import view.BookmarkView;
import view.TranslateView;
import view.LoginView;
import view.SignupView;
import view.ViewManager;

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
    private final UserFactory userFactory = new CommonUserFactory();
    private final TranslateFactory translateFactory = new TranslateFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private TranslateViewModel translateViewModel;
    private TranslateView translateView;
    private LoginView loginView;
    private HistoryViewModel historyViewModel;
    private HistoryView historyView;
    private BookmarkViewModel bookmarkViewModel;
    private BookmarkView bookmarkView;

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
    public AppBuilder addTranslateView() {
        loggedInViewModel = new LoggedInViewModel();
        translateViewModel = new TranslateViewModel();
        historyViewModel = new HistoryViewModel();
        translateView = new TranslateView(loggedInViewModel, translateViewModel, historyViewModel);
        cardPanel.add(translateView, translateView.getViewName());
        return this;
    }

    /**
     * Adds the History View to the application.
     * @return this builder
     */
    public AppBuilder addHistoryView() {
        historyViewModel = new HistoryViewModel();
        historyView = new HistoryView(historyViewModel);
        cardPanel.add(historyView, historyView.getViewName());
        return this;
    }

    /**
     * Adds the Bookmark View to the application.
     * @return this builder
     */
    public AppBuilder addBookmarkView() {
        bookmarkViewModel = new BookmarkViewModel();
        bookmarkView = new BookmarkView(bookmarkViewModel);
        cardPanel.add(bookmarkView, bookmarkView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
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
                loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel, viewManagerModel, bookmarkViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        translateView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        translateView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addHistoryUseCase() {
        final HistoryOutputBoundary historyOutputBoundary =
                new HistoryPresenter(historyViewModel, loggedInViewModel,
                        viewManagerModel);

        final HistoryInputBoundary historyInteractor =
                new HistoryInteractor(userDataAccessObject, historyOutputBoundary);

        final HistoryController historyController =
                new HistoryController(historyInteractor);
        historyView.setHistoryController(historyController);
        translateView.setHistoryController(historyController);
        return this;
    }

    public AppBuilder addTranslateUseCase() {
        final TranslatorOutputBoundary translateOutputBoundary =
                new TranslatePresenter(viewManagerModel, translateViewModel);

        final TranslatorInputBoundary translatorInteractor =
                new TranslatorInteractor(userDataAccessObject, translateOutputBoundary, translateFactory);

        final TranslateController translateController =
                new TranslateController(translatorInteractor);
        translateView.setTranslateController(translateController);
        return this;
    }

    /**
     * Adds the Bookmark Use Case to the application.
     * @return this builder
     */
    public AppBuilder addBookmarkUseCase() {
        final BookmarkOutputBoundary bookmarkOutputBoundary = new BookmarkPresenter(bookmarkViewModel);
        final BookmarkFactory bookmarkFactory = new BookmarkFactory();

        final BookmarkInputBoundary bookmarkInteractor = new BookmarkInteractor(
                userDataAccessObject, bookmarkOutputBoundary, bookmarkFactory);

        final BookmarkController bookmarkController = new BookmarkController(bookmarkInteractor);
        bookmarkView.setBookmarkController(bookmarkController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
