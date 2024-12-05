package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.*;

import entity.*;
import entity.Movie;
import entity.UserWatchlist;
import interface_adapter.add_to_watchlist.AddToWatchlistController;
import interface_adapter.leave_review.LeaveReviewController;
import interface_adapter.movie.MovieController;
import interface_adapter.movie.MovieState;
import interface_adapter.movie.MovieViewModel;

/**
 * The View for when the user views a movie.
 */
public class MovieView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName;

    private final MovieViewModel movieViewModel;
    private MovieController movieController;
    private LeaveReviewController leaveReviewController;
    private AddToWatchlistController addToWatchlistController;

    private final JButton homeButton;
    private final JPanel infoPanel;

    private JButton watchedButton;
    private JButton leaveReviewButton;
    private JButton addToListButton;
    private JButton userReviewsButton;

    /**
     * The view for a movie.
     * @param movieViewModel the view model for our movie screen.
     */
    public MovieView(MovieViewModel movieViewModel) {
        this.movieViewModel = movieViewModel;
        this.viewName = movieViewModel.getViewName();

        // Register as an observer
        this.movieViewModel.addPropertyChangeListener(this);

        // Set the main layout to BorderLayout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // create the bottom buttoms and their panel and Functionality
        this.watchedButton = new JButton(MovieViewModel.PWL_LABEL);
        watchedButton.setBackground(new Color(238, 232, 170));
        watchedButton.setOpaque(true);
        watchedButton.setBorderPainted(false);
        watchedButton.setForeground(Color.BLACK);

        this.leaveReviewButton = new JButton(MovieViewModel.LEAVE_REVIEW_LABEL);
        leaveReviewButton.setBackground(new Color(238, 232, 170));
        leaveReviewButton.setOpaque(true);
        leaveReviewButton.setBorderPainted(false);
        leaveReviewButton.setForeground(Color.BLACK);

        this.addToListButton = new JButton(MovieViewModel.ADD_TO_LIST_LABEL);
        addToListButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToListButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToListButton.setBackground(new Color(173, 216, 230));
        addToListButton.setOpaque(true);
        addToListButton.setBorderPainted(false);
        addToListButton.setForeground(Color.BLACK);

        this.userReviewsButton = new JButton(MovieViewModel.USER_REVIEWS_LABEL);
        userReviewsButton.setBackground(new Color(200, 210, 250));
        userReviewsButton.setOpaque(true);
        userReviewsButton.setBorderPainted(false);
        userReviewsButton.setForeground(Color.BLACK);

        final JPanel bottomButtons = new JPanel();
        bottomButtons.add(watchedButton);
        bottomButtons.add(addToListButton);
        bottomButtons.add(userReviewsButton);

        watchedButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    final MovieState currentState = movieViewModel.getState();
                    final User currUser = currentState.getCurrentUser();

                    addToWatchlistController.execute(currUser, currUser.getPwl(),
                            currentState.getCurrentMovie());

                    bottomButtons.remove(watchedButton);
                    bottomButtons.add(leaveReviewButton);

                    bottomButtons.revalidate();
                    bottomButtons.repaint();
                }
        );

        leaveReviewButton.addActionListener(
                evt -> movieController.switchToLeaveReviewView());

        addToListButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addToListButton)) {
                            addToListPopUpView();
                        }
                    }
                });

        this.add(bottomButtons, BorderLayout.NORTH);

        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel titleLabel2 = new JLabel(MovieViewModel.TITLE_LABEL);
        titleLabel2.setFont(new Font("SansSerif", Font.BOLD, 18));
        titlePanel.add(titleLabel2);
        this.add(titlePanel, BorderLayout.NORTH);

        this.infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        this.add(infoPanel, BorderLayout.CENTER);

    }

    // helper method to create the info panel
    private void createInfoPanel() {
        // Labels and information for the movie
        final String titleInfo = movieViewModel.getState().getTitle();
        final String overviewInfo = movieViewModel.getState().getOverview();
        final String genreInfo = String.join(", ", movieViewModel.getState().getGenres());
        final String starRatingsInfo = String.valueOf(movieViewModel.getState().getStarRating());
        final String voterAverageInfo = movieViewModel.getState().getExternalStarRating();

        // Add all panels to the infoPanel
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(createInfoRow(MovieViewModel.MOVIE_LABEL, titleInfo));
        infoPanel.add(createInfoRow(MovieViewModel.OVERVIEW_LABEL, overviewInfo));
        infoPanel.add(createInfoRow(MovieViewModel.GENRE_LABEL, genreInfo));
        infoPanel.add(createInfoRow(MovieViewModel.OUR_RATINGS_LABEL, starRatingsInfo));
        infoPanel.add(createInfoRow(MovieViewModel.VOTER_AVERAGE_LABEL, voterAverageInfo));

        this.add(infoPanel, BorderLayout.CENTER);

    }

    private JPanel createInfoRow(String labelText, String infoText) {

        final JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.add(new JLabel(labelText), BorderLayout.NORTH);

        final JTextArea infoArea = new JTextArea(infoText);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);
        infoArea.setBackground(rowPanel.getBackground());

        rowPanel.add(infoArea, BorderLayout.CENTER);

        return rowPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        infoPanel.removeAll();

        createInfoPanel();

        infoPanel.revalidate();
        infoPanel.repaint();
    }

    /**
     * View for Add to List Pop-Up Window.
     */
    private void addToListPopUpView() {
        final JPanel panel = new JPanel(new BorderLayout());

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        final MovieState currentState = movieViewModel.getState();

        final ArrayList<UserWatchlist> watchlists = currentState.getCurrentUser().getWatchlists();

        for (int i = 0; i < watchlists.size(); i++) {
            final int ind = i;
            final UserWatchlist watchlist = watchlists.get(i);
            final JButton listButton = new JButton(watchlist.getListName());
            listButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            listButton.setBackground(new Color(173, 216, 230));
            listButton.setOpaque(true);
            listButton.setBorderPainted(false);
            listButton.setForeground(Color.BLACK);
            listButton.setPreferredSize(new Dimension(150, 40));
            listButton.setMinimumSize(new Dimension(150, 40));
            listButton.setMaximumSize(new Dimension(150, 40));

            final Movie currentMovie = currentState.getCurrentMovie();

            if (watchlist.contains(currentMovie)) {
                listButton.setEnabled(false);
            }
            else {
                listButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final User currUser = currentState.getCurrentUser();

                        addToWatchlistController.execute(currUser, ind, currentMovie);

                        listButton.setEnabled(false);
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

        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        panel.setPreferredSize(new Dimension(300, 500));

        JOptionPane.showOptionDialog(this, panel, "My Lists", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{}, null);
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

}
