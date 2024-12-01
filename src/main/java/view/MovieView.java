package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_watchlist.AddToWatchlistController;
import entity.CommonUserWatchlist;
import entity.Movie;
import entity.UserWatchlist;
import entity.Watchlist;
import interface_adapter.leave_review.LeaveReviewController;
import interface_adapter.leave_review.LeaveReviewState;
import interface_adapter.movie.MovieController;
import interface_adapter.movie.MovieState;
import interface_adapter.movie.MovieViewModel;
import interface_adapter.watchlists.WatchlistsState;

/**
 * The View for when the user views a movie.
 */
public class MovieView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Movie Information";

    private final MovieViewModel movieViewModel;
    private MovieController movieController;
    private LeaveReviewController leaveReviewController;
    private AddToWatchlistController addToWatchlistController;


    private final JButton backButton;
    private final JButton homeButton;

    private final JButton watchedButton;
    private final JButton addToListButton;
    private final JButton userReviewsButton;

    // labels for info
    private final JLabel titleLabel;
    private final JLabel posterLabel;
    private final JLabel overviewLabel;
    private final JLabel genreLabel;
    private final JLabel ourRatingsLabel;
    private final JLabel voterAverageLabel;

    public MovieView(MovieViewModel movieViewModel) {
        this.movieViewModel = movieViewModel;
        this.movieViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(MovieViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Home button section
        this.homeButton = new JButton(MovieViewModel.HOME_LABEL);
        homeButton.addActionListener(
                evt -> movieController.switchToHomeView()
        );

        final JPanel homePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Fixed the naming
        homePanel.add(this.homeButton);
        homePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(homePanel);

        // title and poster panel

        this.titleLabel = new JLabel(MovieViewModel.MOVIE_LABEL);
        // Center align the poster
        this.posterLabel = new JLabel();
        this.posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // middle panel with info about movie
        this.overviewLabel = new JLabel(MovieViewModel.OVERVIEW_LABEL);
        this.genreLabel = new JLabel(MovieViewModel.GENRE_LABEL);
        this.ourRatingsLabel = new JLabel(MovieViewModel.OUR_RATINGS_LABEL);
        this.voterAverageLabel = new JLabel(MovieViewModel.VOTER_AVERAGE_LABEL);

        final JPanel infoPanel = new JPanel();
        infoPanel.add(titleLabel);
        infoPanel.add(overviewLabel);
        infoPanel.add(genreLabel);
        infoPanel.add(ourRatingsLabel);
        infoPanel.add(voterAverageLabel);

        // Poster Panel
        final JPanel posterPanel = new JPanel();
        posterPanel.add(this.posterLabel);

        // bottom buttons
        // TODO add functionality for the bottom buttons
        this.watchedButton = new JButton(MovieViewModel.PWL_LABEL);
        this.addToListButton = new JButton(MovieViewModel.ADD_TO_LIST_LABEL);
        this.userReviewsButton = new JButton(MovieViewModel.USER_REVIEWS_LABEL);
        this.backButton = new JButton(MovieViewModel.BACK_BUTTON_LABEL);

        final JPanel bottomButtons = new JPanel();
        bottomButtons.add(watchedButton);
        bottomButtons.add(addToListButton);
        bottomButtons.add(userReviewsButton);

        // add all components
        this.add(homeButton);
        this.add(title);
        this.add(infoPanel);
        this.add(posterPanel);
        this.add(bottomButtons);

        watchedButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(watchedButton)) {
                            final MovieState currentState = movieViewModel.getState();
                            final User currUser = currentState.getCurrentUser();

                            final Watchlist pwl = currUser.getPwl();

                            final String movieTitle = currentState.getCurrentMovie().getTitle();

                            addToWatchlistController.execute(currUser, pwl, movieTitle);
                            watchedButton.setText(MovieViewModel.LEAVE_REVIEW_LABEL);
                            for (ActionListener al : watchedButton.getActionListeners()) {
                                watchedButton.removeActionListener(al);
                            }

                            watchedButton.addActionListener (
                                    new ActionListener() {
                                        public void actionPerformed(ActionEvent evt) {
                                            if (evt.getSource().equals(watchedButton)) {
                                                    movieController.switchToLeaveReviewView();
                                            }
                                        }
                                    }
                            );
                        }
                    }
                }
        );

        addToListButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addToListButton)) {
                            addToListPopUpView();
                        }
                    }
                });
    }

    /**
     * View for Add to List Pop-Up Window.
     */
    private void addToListPopUpView() {
        final JPanel panel = new JPanel(new BorderLayout());

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        final MovieState currentState = movieViewModel.getState();

        final ArrayList<Watchlist> watchlists = currentState.getCurrentUser().getWatchlists();

        for (Watchlist watchlist : watchlists) {
            final String listName = watchlist.getListName();
            final JButton listButton = new JButton(listName);
            final Movie currentMovie = currentState.getCurrentMovie();

            if (watchlist.contains(currentMovie)) {
                listButton.setEnabled(false);
            }
            else {
                listButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(listButton)) {

                            final MovieState currentState = movieViewModel.getState();
                            final User currUser = currentState.getCurrentUser();

                            addToWatchlistController.execute(currUser, watchlist, currentMovie.getTitle());

                            listButton.setEnabled(false);
                        }
                    }
                });
            }

            buttonPanel.add(listButton);
        }

        // create Close button
        final JButton closeButton = new JButton("Close");

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(closeButton)) {
                    SwingUtilities.getWindowAncestor(closeButton).dispose();
                }
            }
        });

        // Add items to panel
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        JOptionPane.showOptionDialog(this, panel, "My Lists", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{}, null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MovieState state = (MovieState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(MovieState state) {
        //
    }

    public void setLeaveReviewController(LeaveReviewController leaveReviewController) {
        this.leaveReviewController = leaveReviewController;
    }

    public void setMovieController(MovieController movieController) {
        this.movieController = movieController;
    }

    public String getViewName() {
        return viewName;
    }

    public void setAddToWatchlistController(AddToWatchlistController addToWatchlistController) {
        this.addToWatchlistController = addToWatchlistController;
    }


    private void setPoster(String posterUrl) {
        try {
            // Load image from URL
            final ImageIcon icon = new ImageIcon(new URL(posterUrl));
            // Optionally scale the image to fit the JLabel size
            final Image scaledImage = icon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
            this.posterLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            // Set a default placeholder if the image fails to load
            this.posterLabel.setText("Poster not available");
            e.printStackTrace();
        }
    }
}

