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

import interface_adapter.home.HomeController;
import interface_adapter.home.HomeViewModel;
import interface_adapter.home.LoggedInState;
import interface_adapter.logout.LogoutController;
import interface_adapter.watchlists.WatchlistsController;

/**
 * The Home View for when the user is logged into the program.
 */
public class HomeView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final HomeViewModel homeViewModel;
    private HomeController homeController;
    private LogoutController logoutController;

    private final JLabel username;

    private final JButton logOut;

    private JButton recommendations = new JButton("Recommendations");
    private JButton myWatchlists = new JButton("My Watchlists");
    private JButton myReviews = new JButton("My Reviews");

    private final JTextField searchInputField = new JTextField(50);

    public HomeView(HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Movies4U");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel("Search:"), searchInputField);

        username = new JLabel();

        final JPanel logOutButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logOut = new JButton("Log Out");
        logOutButton.add(logOut);
        logOut.setAlignmentX(Component.LEFT_ALIGNMENT);

        final JPanel bottomButtons = new JPanel();
        recommendations = new JButton("Recommendations");
        myWatchlists = new JButton("My Watchlists");
        myWatchlists.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(myWatchlists)) {
                        final String uname = homeViewModel.getState().getUsername();
                        this.homeController.switchToWatchlistsView(uname);
                    }
                }
        );

        myReviews = new JButton("My Reviews");
        bottomButtons.add(recommendations);
        bottomButtons.add(myWatchlists);
        bottomButtons.add(myReviews);

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

//        changePassword.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                evt -> {
//                    if (evt.getSource().equals(changePassword)) {
//                        final LoggedInState currentState = homeViewModel.getState();
//
//                        this.changePasswordController.execute(
//                                currentState.getUsername(),
//                                currentState.getPassword()
//                        );
//                    }
//                }
//        );

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
        this.add(searchInfo);
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
}