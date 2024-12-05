
package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DBBookClubDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import data_access.JacksonTranslator;
import data_access.OpenLibraryClient;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_message.AddMessageController;
import interface_adapter.add_message.AddMessagePresenter;
import interface_adapter.add_message.AddMessageViewModel;
import interface_adapter.bookclub_list.BookClubListController;
import interface_adapter.bookclub_list.BookClubListPresenter;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.create_club.CreateClubController;
import interface_adapter.create_club.CreateClubPresenter;
import interface_adapter.create_club.CreateClubViewModel;
import interface_adapter.exit_bookclub.ExitClubController;
import interface_adapter.exit_bookclub.ExitClubPresenter;
import interface_adapter.join_club.JoinClubController;
import interface_adapter.join_club.JoinClubPresenter;
import interface_adapter.join_club.JoinClubViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.my_clubs.MyClubsController;
import interface_adapter.my_clubs.MyClubsPresenter;
import interface_adapter.my_clubs.MyClubsViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search.SearchedViewModel;
import interface_adapter.show_books.ShowBooksController;
import interface_adapter.show_books.ShowBooksPresenter;
import interface_adapter.show_books.ShowBooksViewModel;
import interface_adapter.show_notes.ShowNotesController;
import interface_adapter.show_notes.ShowNotesPresenter;
import interface_adapter.show_notes.ShowNotesViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.add_message.AddMessageInputBoundary;
import use_case.add_message.AddMessageInteractor;
import use_case.add_message.AddMessageOutputBoundary;
import use_case.bookclub_list.BookClubInputBoundary;
import use_case.bookclub_list.BookClubInteractor;
import use_case.bookclub_list.BookClubOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.create_club.CreateClubInputBoundary;
import use_case.create_club.CreateClubInteractor;
import use_case.create_club.CreateClubOutputBoundary;
import use_case.exit_bookclub.ExitClubInputBoundary;
import use_case.exit_bookclub.ExitClubInteractor;
import use_case.exit_bookclub.ExitClubOutputBoundary;
import use_case.join_club.JoinClubInputBoundary;
import use_case.join_club.JoinClubInteractor;
import use_case.join_club.JoinClubOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.my_clubs.MyClubsInputBoundary;
import use_case.my_clubs.MyClubsInteractor;
import use_case.my_clubs.MyClubsOutputBoundary;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.show_Notes.ShowNotesInputBoundary;
import use_case.show_Notes.ShowNotesInteractor;
import use_case.show_Notes.ShowNotesOutputBoundary;
import use_case.show_books.ShowBooksInputBoundary;
import use_case.show_books.ShowBooksInteractor;
import use_case.show_books.ShowBooksOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.AddMessageView;
import view.CreateClubView;
import view.JoinClubView;
import view.LoggedInView;
import view.LoginView;
import view.MyClubsView;
import view.SearchView;
import view.SearchedView;
import view.ShowBooksView;
import view.ShowNotesView;
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
    private final UserFactory userFactory = new UserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private final JacksonTranslator translator = new JacksonTranslator();
    private final DBBookClubDataAccessObject bookClubDataAccessObject = new DBBookClubDataAccessObject(translator);

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoginView loginView;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private MyClubsViewModel myClubsViewModel;
    private MyClubsView myClubsView;
    private ShowNotesViewModel showNotesViewModel;
    private ShowNotesView showNotesView;
    private AddMessageViewModel addMessageViewModel;
    private AddMessageView addMessageView;
    private JoinClubView joinClubView;
    private CreateClubViewModel createClubViewModel;
    private CreateClubView createClubView;

    private JoinClubViewModel joinClubViewModel;

    private ShowBooksViewModel showBooksViewModel;
    private ShowBooksView showBooksView;

    private SearchedViewModel searchedViewModel = new SearchedViewModel();
    private SearchedView searchedView;
    private SearchViewModel searchViewModel = new SearchViewModel();
    private SearchView searchView;
    private SearchController searchController;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the JoinClub to the application.
     * @return this builder
     */

    public AppBuilder addCreateClubView() {
        createClubViewModel = new CreateClubViewModel();
        createClubView = new CreateClubView(createClubViewModel, viewManagerModel);
        cardPanel.add(createClubView, createClubView.getViewName());
        return this;
    }

    /**
     * Adds the show books view to the application.
     * @return this builder
     */
    public AppBuilder addShowBooksView() {
        showBooksViewModel = new ShowBooksViewModel();
        showBooksView = new ShowBooksView(showBooksViewModel, viewManagerModel);
        cardPanel.add(showBooksView, showBooksView.getViewName());
        return this;
    }

    /**
     * Adds the Join club view to the application.
     * @return this builder
     */
    public AppBuilder addJoinClubView() {
        joinClubViewModel = new JoinClubViewModel();
        joinClubView = new JoinClubView(joinClubViewModel, viewManagerModel);
        cardPanel.add(joinClubView, joinClubView.getViewName());
        return this;

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
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel, searchedViewModel, searchViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the MyClubs view to the application.
     * @return this builder
     */
    public AppBuilder addMyClubsView() {
        myClubsViewModel = new MyClubsViewModel();
        myClubsView = new MyClubsView(myClubsViewModel, viewManagerModel);
        cardPanel.add(myClubsView, myClubsView.getViewName());
        return this;
    }

    /**
     * Adds the AddMessage View to the application.
     * @return this builder
     */
    public AppBuilder addAddMessageView() {
        addMessageViewModel = new AddMessageViewModel();
        addMessageView = new AddMessageView(addMessageViewModel, viewManagerModel);
        cardPanel.add(addMessageView, addMessageView.getViewName());
        return this;
    }

    /**
     * Adds the ShowNotes view to the application.
     * @return this builder
     */
    public AppBuilder addShowNotesView() {
        showNotesViewModel = new ShowNotesViewModel();
        showNotesView = new ShowNotesView(showNotesViewModel, viewManagerModel);
        cardPanel.add(showNotesView, showNotesView.getViewName());
        return this;
    }

    /**
     * Adds the Searched view to the application.
     * @return this builder
     */
    public AppBuilder addSearchedView() {
        searchedView = new SearchedView(searchedViewModel, viewManagerModel);

        searchedView.addPropertyChangeListener(evt -> {
            if ("backToSearch".equals(evt.getPropertyName())) {
                viewManagerModel.setState(loginView.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

        cardPanel.add(searchedView, searchedView.getViewName());
        return this;
    }

    /**
     * Adds the Searched view to the application.
     * @return this builder
     */
    public AppBuilder addSearchView() {
        searchView = new SearchView(searchViewModel);
        cardPanel.add(searchView, searchView.getViewName());
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
                loggedInViewModel, loginViewModel, joinClubViewModel, addMessageViewModel, myClubsViewModel,
                createClubViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loggedInView.setLoginController(loginController);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the MyClubs Use Case to the application.
     * @return this builder
     */
    public AppBuilder addMyClubsUsecase() {
        final MyClubsOutputBoundary myClubsOutputBoundary = new MyClubsPresenter(myClubsViewModel,
                showNotesViewModel, loggedInViewModel, viewManagerModel);

        final MyClubsInputBoundary myClubsInteractor = new MyClubsInteractor(bookClubDataAccessObject,
                myClubsOutputBoundary);

        final MyClubsController myClubsController = new MyClubsController(myClubsInteractor);

        myClubsView.setMyClubsController(myClubsController);
        loggedInView.setMyClubsController(myClubsController);
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
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the AddMessage Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAddMessagesUseCase() {
        final AddMessageOutputBoundary addMessageOutputBoundary = new AddMessagePresenter(addMessageViewModel);
        final AddMessageInputBoundary addMessageInteractor = new AddMessageInteractor(bookClubDataAccessObject,
                addMessageOutputBoundary);
        final AddMessageController addMessageController = new AddMessageController(addMessageInteractor);
        addMessageView.setAddMessageController(addMessageController);
        return this;
    }

    /**
     * Adds the ShowNotes Use Case to the application.
     * @return this builder
     */
    public AppBuilder addShowNotesUseCase() {
        final ShowNotesOutputBoundary showNotesOutputBoundary =
                new ShowNotesPresenter(showNotesViewModel, viewManagerModel, addMessageViewModel);
        final ShowNotesInputBoundary showNotesInteractor =
                new ShowNotesInteractor(bookClubDataAccessObject, showNotesOutputBoundary);
        final ShowNotesController showNotesController =
                new ShowNotesController(showNotesInteractor);
        showNotesView.setShowNotesController(showNotesController);
        return this;
    }

    /**
     * Adds the bookClub usecase to the application.
     * @return this builder
     */

    public AppBuilder addBookClubListUseCase() {

        final BookClubOutputBoundary bookClubOutputBoundary =
                new BookClubListPresenter(loggedInViewModel, viewManagerModel, joinClubViewModel);

        final BookClubInputBoundary bookClubInteractor = new BookClubInteractor(bookClubOutputBoundary,
                bookClubDataAccessObject);
        final BookClubListController bookClubListController = new BookClubListController(bookClubInteractor);
        loggedInView.setBookClubListController(bookClubListController);

        return this;
    }
    /**
     * Adds the joinclub usecase to the application.
     * @return this builder
     */

    public AppBuilder addJoinClubUseCase() {
        final JoinClubOutputBoundary joinClubOutputBoundary =
                new JoinClubPresenter(viewManagerModel, loggedInViewModel, joinClubViewModel);
        final JoinClubInputBoundary joinClubInteractor =
                new JoinClubInteractor(joinClubOutputBoundary, bookClubDataAccessObject);
        final JoinClubController joinClubController = new JoinClubController(joinClubInteractor);
        joinClubView.setJoinClubController(joinClubController);
        return this;
    }

    /**
     * Adds the create club usecase to the application.
     * @return this builder
     */
    public AppBuilder addCreateClubUseCase() {
        final CreateClubOutputBoundary createClubOutputBoundary =
                new CreateClubPresenter(createClubViewModel, viewManagerModel, loggedInViewModel);
        final CreateClubInputBoundary createClubInteractor =
                new CreateClubInteractor(createClubOutputBoundary, bookClubDataAccessObject);
        final CreateClubController createClubController = new CreateClubController(createClubInteractor);
        createClubView.setController(createClubController);
        return this;
    }

    /**
     * Adds the exit club usecase to the application.
     * @return this builder
     */
    public AppBuilder addExitBookClubUseCase() {
        final ExitClubOutputBoundary exitClubOutputBoundary =
                new ExitClubPresenter(loggedInViewModel, viewManagerModel);
        final ExitClubInputBoundary exitClubInteractor =
                new ExitClubInteractor(exitClubOutputBoundary, bookClubDataAccessObject);
        final ExitClubController exitClubController = new ExitClubController(exitClubInteractor);
        myClubsView.setExitClubController(exitClubController);
        return this;
    }

    /**
     * Adds the books list usecase to the application.
     * @return this builder
     */
    public AppBuilder addBooksListUseCase() {
        final ShowBooksOutputBoundary showBooksOutputBoundary =
                new ShowBooksPresenter(viewManagerModel, showBooksViewModel);
        final ShowBooksInputBoundary showBooksInteractor = new
                ShowBooksInteractor(showBooksOutputBoundary, bookClubDataAccessObject);
        final ShowBooksController showBooksController = new ShowBooksController(showBooksInteractor);
        myClubsView.setShowBooksController(showBooksController);
        return this;
    }

    /**
     * Adds the exit club usecase to the application.
     * @return this builder
     */
    public AppBuilder addSearchUseCase() {
        final SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel,
                viewManagerModel, searchedViewModel);
        final OpenLibraryClient apiCaller = new OpenLibraryClient();
        final SearchInputBoundary searchInteractor = new SearchInteractor(apiCaller, searchOutputBoundary);
        final SearchController searchControllerP = new SearchController(searchInteractor);
        searchView.setSearchController(searchControllerP);
        searchedView.setSearchController(searchControllerP);
        loggedInView.setSearchController(searchControllerP);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Virtual Book Club application");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();
        return application;
    }

}
