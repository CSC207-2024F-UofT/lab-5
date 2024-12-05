package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.*;

import entity.Movie;
import interface_adapter.watchlist.WatchlistController;
import interface_adapter.watchlist.WatchlistViewModel;
import interface_adapter.watchlist.remove.RemoveMovieController;

/**
 * The View for the screen showing a content of a watchlist.
 */
public class WatchlistView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName;

    private final WatchlistViewModel watchlistViewModel;
    private WatchlistController watchlistController;
    private RemoveMovieController removeMovieController;

    private JLabel title;
    private JPanel topLine;
    private JButton addMovie;
    private JPanel movieButtons;

    public WatchlistView(WatchlistViewModel watchlistViewModel) {
        this.watchlistViewModel = watchlistViewModel;
        this.viewName = watchlistViewModel.getViewName();
        watchlistViewModel.addPropertyChangeListener(this);

        // Set the main layout to BorderLayout for flexibility
        this.setLayout(new BorderLayout());

        // Top panel for back, title, and home buttons
        this.topLine = new JPanel(new BorderLayout());
        topLine.setPreferredSize(new Dimension(400, 40));

        // Back button on the left
        final JButton back = new JButton(watchlistViewModel.BACK_LABEL);
        back.addActionListener(evt -> watchlistController.backToWatchlistsView(this.watchlistViewModel.getState().getCurrentUser()));
        topLine.add(back, BorderLayout.WEST);

        // Title in the center
        this.title = new JLabel(watchlistViewModel.getState().getWatchlistName(), JLabel.CENTER);
        title.setFont(new Font("Serif Sans", Font.BOLD, 20));
        topLine.add(title, BorderLayout.CENTER);

        // Home button on the right
        final JButton home = new JButton(watchlistViewModel.HOME_LABEL);
        home.addActionListener(evt -> watchlistController.switchToHomeView());
        topLine.add(home, BorderLayout.EAST);

        // Add the topLine to the top of the main layout
        this.add(topLine, BorderLayout.NORTH);

        // Center panel for the Add Movie button and movie buttons
        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add Movie button
        this.addMovie = new JButton(watchlistViewModel.ADD_MOVIE_LABEL);
        addMovie.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMovie.setAlignmentX(Component.CENTER_ALIGNMENT);
        addMovie.setBackground(new Color(173, 216, 230));
        addMovie.setOpaque(true);
        addMovie.setBorderPainted(false);
        addMovie.setForeground(Color.BLACK);

        addMovie.addActionListener(evt -> watchlistController.switchToMovieSearchView(watchlistViewModel.getState().getCurrentUser()));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(addMovie);

        // Panel for movie buttons
        this.movieButtons = new JPanel();
        this.movieButtons.setLayout(new BoxLayout(this.movieButtons, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(this.movieButtons);

        // Add centerPanel to the center of the layout
        this.add(centerPanel, BorderLayout.CENTER);

        // Set background color
        this.setPreferredSize(new Dimension(400, 300));
        final Color backcolor = new Color(255, 255, 255);
        this.setBackground(backcolor);
    }

    private void updateWatchlist() {
        this.title.setText(this.watchlistViewModel.getState().getWatchlistName());
        this.movieButtons.removeAll();

        final List<Movie> movies = watchlistViewModel.getState().getWatchlist().getMovies();
        for (int i = 0; i < movies.size(); i++) {
            final JPanel buttons = new JPanel();
            buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
            final JButton movie = new JButton(movies.get(i).getTitle());
            movie.setBackground(new Color(197, 224, 181));
            movie.setOpaque(true);
            movie.setBorderPainted(false);
            movie.setForeground(Color.BLACK);

            movie.setPreferredSize(new Dimension(150, 40));
            movie.setMinimumSize(new Dimension(150, 40));
            movie.setMaximumSize(new Dimension(150, 40));
            final int ind = i;
            movie.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(movie)) {
                            watchlistController.switchToMovieView(watchlistViewModel.getState().getCurrentUser(),
                                    movies.get(ind), ind);

                        }
                    }
            );
            buttons.add(movie);
            final JButton remove = new JButton(watchlistViewModel.REMOVE_LABEL);
            remove.setBackground(new Color(238, 232, 170));
            remove.setOpaque(true);
            remove.setBorderPainted(false);
            remove.setForeground(Color.BLACK);
            remove.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(remove)) {

                            final String currentUser = watchlistViewModel.getState().getCurrentUser();
                            final int watchlistIndex = watchlistViewModel.getState().getWatchlistIndex();

                            removeMovieController.execute(currentUser, watchlistIndex, ind);
                        }
                    }
            );
            buttons.add(remove);
            this.movieButtons.add(buttons);
            this.movieButtons.revalidate();
            this.movieButtons.repaint();

        }
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    public String getViewName() {
        return viewName;
    }

    public void setWatchlistController(WatchlistController controller) {
        this.watchlistController = controller;
    }

    public void setRemoveMovieController(RemoveMovieController removeMovieController) {
        this.removeMovieController = removeMovieController;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateWatchlist();
    }
}
