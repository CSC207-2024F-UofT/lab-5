package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
//    private final JLabel titleLabel;
//    private final JLabel posterLabel;
//    private final JLabel overviewLabel;
//    private final JLabel genreLabel;
//    private final JLabel ourRatingsLabel;
//    private final JLabel voterAverageLabel;

    public MovieView(MovieViewModel movieViewModel) {
        this.movieViewModel = movieViewModel;

        // Register as an observer
        this.movieViewModel.addPropertyChangeListener(this);

        // Set the main layout to BorderLayout
        this.setLayout(new BorderLayout());

        // creates the home button and its functionality
        this.homeButton = new JButton(MovieViewModel.HOME_LABEL);
        homeButton.addActionListener(
                evt -> movieController.switchToHomeView()
        );

        // Home button panel on the left
        final JPanel home = new JPanel(new FlowLayout(FlowLayout.LEFT));
        home.add(homeButton);
        this.add(home, BorderLayout.WEST);

        // create title on centre top
        final JLabel title = new JLabel(MovieViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title, BorderLayout.NORTH);

//        // title and poster panel
//        this.titleLabel = new JLabel(MovieViewModel.MOVIE_LABEL);
//        // Center align the poster
//        this.posterLabel = new JLabel();
//        this.posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        // middle panel with info about movie
//        this.overviewLabel = new JLabel(MovieViewModel.OVERVIEW_LABEL);
//        this.genreLabel = new JLabel(MovieViewModel.GENRE_LABEL);
//        this.ourRatingsLabel = new JLabel(MovieViewModel.OUR_RATINGS_LABEL);
//        this.voterAverageLabel = new JLabel(MovieViewModel.VOTER_AVERAGE_LABEL);
//
//        final JPanel infoPanel = new JPanel();
//        infoPanel.add(titleLabel);
//        infoPanel.add(overviewLabel);
//        infoPanel.add(genreLabel);
//        infoPanel.add(ourRatingsLabel);
//        infoPanel.add(voterAverageLabel);

//        // Poster Panel
//        final JPanel posterPanel = new JPanel();
//        posterPanel.add(this.posterLabel);

        final JPanel infoPanel = createMovieInfoPanel();

        // bottom buttons
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
//        this.add(infoPanel);
//        this.add(posterPanel);
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

    private JPanel createMovieInfoPanel() {
//        final JPanel infoPanel = new JPanel();
//        infoPanel.setLayout(new GridLayout(0, 1));
//
//        // TODO add the poster
//
//        // Labels for the information
//        final JLabel title = new JLabel(MovieViewModel.MOVIE_LABEL);
//        final JLabel overview = new JLabel(MovieViewModel.OVERVIEW_LABEL);
//        final JLabel genres = new JLabel(MovieViewModel.GENRE_LABEL);
//        final JLabel ourRatings = new JLabel(MovieViewModel.OUR_RATINGS_LABEL);
//        final JLabel voterAverage = new JLabel(MovieViewModel.VOTER_AVERAGE_LABEL);
//
//        // information to be displayed
//        final JLabel titleInfo = new JLabel(movieViewModel.getState().getCurrentMovie().getTitle());
////        final JLabel posterInfo = new JLabel(movieViewModel.getState().getCurrentMovie().getPoster());
//        final JLabel overviewInfo = new JLabel(movieViewModel.getState().getCurrentMovie().getOverview());
//
//        final List<String> genresList = movieViewModel.getState().getCurrentMovie().getGenres();
//        String tempGenre = "";
//
//        for (int i = 0; i < genresList.size(); i++) {
//            final String c = genresList.get(i);
//            tempGenre = tempGenre + ", " + c;
//        }
//        final JLabel genreInfo = new JLabel(tempGenre);
//        final JLabel starRatingsInfo = new JLabel(String.valueOf(movieViewModel.getState()
//                .getCurrentMovie().getStarRatings()));
//        final JLabel voterAverageInfo = new JLabel(movieViewModel.getState().getCurrentMovie().getVoterAverage());
//
//        final JPanel titlePanel = new JPanel();
//        titlePanel.add(title);
//        titlePanel.add(titleInfo);
//
//        // TODO: Add poster
////        final JPanel posterPanel = new JPanel();
//
//        final JPanel overviewPanel = new JPanel();
//        overviewPanel.add(overview);
//        overviewPanel.add(overviewInfo);
//
//        final JPanel genresPanel = new JPanel();
//        genresPanel.add(genres);
//        genresPanel.add(genreInfo);
//
//        final JPanel starRatingsPanel = new JPanel();
//        starRatingsPanel.add(ourRatings);
//        starRatingsPanel.add(starRatingsInfo);
//
//        final JPanel voterAveragePanel = new JPanel();
//        voterAveragePanel.add(voterAverage);
//        voterAveragePanel.add(voterAverageInfo);
//
//        // adding each of these
//        infoPanel.add(titlePanel);
//        infoPanel.add(overviewPanel);
//        infoPanel.add(genresPanel);
//        infoPanel.add(starRatingsPanel);
//        infoPanel.add(voterAveragePanel);
//
//        return infoPanel;
        return null;
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
//        try {
//            // Load image from URL
//            final ImageIcon icon = new ImageIcon(new URL(posterUrl));
//            // Optionally scale the image to fit the JLabel size
//            final Image scaledImage = icon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
//            this.posterLabel.setIcon(new ImageIcon(scaledImage));
//        } catch (Exception e) {
//            // Set a default placeholder if the image fails to load
//            this.posterLabel.setText("Poster not available");
//            e.printStackTrace();
    }
}


