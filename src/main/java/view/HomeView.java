
package view;

import java.awt.*;
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

import entity.User;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomeViewModel;
import interface_adapter.home.LoggedInState;
import interface_adapter.logout.LogoutController;
import interface_adapter.search_results.SearchResultsController;
import interface_adapter.my_reviews.MyReviewsController;

/**
 * The Home View for when the user is logged into the program.
 */
public class HomeView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final HomeViewModel homeViewModel;
    private HomeController homeController;
    private LogoutController logoutController;
    private SearchResultsController searchResultsController;
    private MyReviewsController myReviewsController;
    private final JLabel username;

    private final JButton logOut;


    private final JLabel search = new JLabel("Search");

    private JButton searchEnter = new JButton("Find Some Movies");
    private JButton myWatchlists = new JButton("My Watchlists");
    private JButton myReviewsButton = new JButton("My Reviews");

    private final JTextField searchInputField = new JTextField(50);

    public HomeView(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Movies4U");
        title.setFont(new Font("Georgia", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchEnter.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchEnter.setBackground(new Color(173, 216, 230));
        searchEnter.setOpaque(true);
        searchEnter.setBorderPainted(false);
        searchEnter.setForeground(Color.BLACK);

        // creates the enter button
        searchEnter.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(searchEnter)) {
                        final String query = searchInputField.getText();
                        this.homeController.switchToSearchResultsView(query);
                    }
                }
        );

        // create the search bar - contains the search label, the input field, and the enter button
        final JPanel searchBar = new JPanel();
        // searchBar.add(search);
        // searchBar.add(searchInputField);
        searchBar.add(searchEnter);

        username = new JLabel();

        final JPanel logOutButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logOut = new JButton("Log Out");
        logOutButton.add(logOut);
        logOut.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JPanel bottomButtons = new JPanel();

        myWatchlists = new JButton("My Watchlists");
        myWatchlists.setBackground(new Color(238, 232, 170));
        myWatchlists.setOpaque(true);
        myWatchlists.setBorderPainted(false);
        myWatchlists.setForeground(Color.BLACK);
        myWatchlists.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(myWatchlists)) {
                        final String uname = homeViewModel.getState().getUsername();
                        this.homeController.switchToWatchlistsView(uname);
                    }
                }
        );

        myReviewsButton = new JButton("My Reviews");
        myReviewsButton.setBackground(new Color(230, 230, 250));
        myReviewsButton.setOpaque(true);
        myReviewsButton.setBorderPainted(false);
        myReviewsButton.setForeground(Color.BLACK);

        bottomButtons.add(myWatchlists);
        bottomButtons.add(myReviewsButton);
        // Add ActionListener to trigger the controller
        // Add ActionListener to trigger the controller
        myReviewsButton.addActionListener(evt -> {
          final User user = homeController.getUser(homeViewModel.getState().getUsername());
            myReviewsController.getReviews(user);
        });


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        searchInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoggedInState currentState = homeViewModel.getState();
                currentState.setPassword(searchInputField.getText());
                homeViewModel.setState(currentState);
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

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        // 1. get the state out of the loggedInViewModel. It contains the username.
                        // 2. Execute the logout Controller.
                        final String uname = homeViewModel.getState().getUsername();
                        this.logoutController.execute(uname);
                    }
                }
        );

        this.add(logOutButton);
        this.add(title);
        this.add(searchBar);
        this.add(bottomButtons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setHomeController(HomeController controller) {
        this.homeController = controller;
    }

    public void setMyReviewsController(MyReviewsController my_reviewsController){ this.myReviewsController = my_reviewsController; }

}