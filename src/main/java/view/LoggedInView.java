package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.bookclub_list.BookClubListController;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.logout.LogoutController;
import interface_adapter.my_clubs.MyClubsController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search.SearchedViewModel;

/**
 * The View for when the user is logged into the program.
 */

public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private MyClubsController myClubsController;
    private LogoutController logoutController;
    private LoginController loginController;

    private BookClubListController bookClubListController;

    private final JLabel username;

    private final JButton logOut;
    private final JButton myClubs;
    private final JButton createClub;
    private final JButton joinClub;

    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;

    private final SearchedViewModel searchedViewModel;
    private final SearchViewModel searchViewModel;
    private final JTextField queryInputField = new JTextField(30);
    private final JButton searchButton;

    private SearchController searchController;

    public LoggedInView(LoggedInViewModel loggedInViewModel,
                        SearchedViewModel searchedViewModel, SearchViewModel searchViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.searchedViewModel = searchedViewModel;
        this.searchViewModel = searchViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        myClubs = new JButton("My Clubs");
        buttons.add(myClubs);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        joinClub = new JButton("Proceed to Join a Club");
        buttons.add(joinClub);

        createClub = new JButton("Create Club");

        final LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel("Search Query"), queryInputField);

        final JPanel button = new JPanel();
        searchButton = new JButton("Search");
        button.add(searchButton);

        searchButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(searchButton)) {
                            final SearchState currentState = searchViewModel.getState();

                            searchController.startSearch(currentState.getQuery());
                        }
                    }
                }
        );

        queryInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SearchState currentState = searchViewModel.getState();
                currentState.setQuery(queryInputField.getText());
                searchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = loggedInViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loggedInViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final LoggedInState currentState = loggedInViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                        logoutController.execute(loggedInViewModel.getState().getUsername());
                    }
                }
        );
        joinClub.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        bookClubListController.execute();
                    }
                }
        );

        myClubs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myClubsController.execute(loggedInViewModel.getState().getUsername());
            }
        });

        createClub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginController.switchToCreateClubView();
            }
        });

        buttons.add(createClub);

        final JPanel changePasswordPanel = new JPanel();
        changePasswordPanel.add(passwordInfo);
        changePasswordPanel.add(passwordErrorField);
        changePasswordPanel.add(changePassword);

        final JPanel usernameInfoPanel = new JPanel();
        usernameInfoPanel.add(usernameInfo);
        usernameInfoPanel.add(username);

        title.setBackground(Color.WHITE);
        usernameInfoPanel.setBackground(Color.WHITE);
        buttons.setBackground(Color.WHITE);
        changePasswordPanel.setBackground(Color.WHITE);
        passwordInfo.setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);

        this.add(title);
        this.add(usernameInfoPanel);
        this.add(buttons);
        this.add(changePasswordPanel);
        this.add(searchInfo);
        this.add(searchButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("error")) {
            JOptionPane.showMessageDialog(null, "You are not a member of any clubs");

        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setMyClubsController(MyClubsController myClubsController) {
        this.myClubsController = myClubsController;
    }

    public void setBookClubListController(BookClubListController bookClubListController) {
        this.bookClubListController = bookClubListController;
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
