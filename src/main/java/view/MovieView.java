package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import entity.Watchlist;
import interface_adapter.movie.MovieController;
import interface_adapter.movie.MovieViewModel;
import interface_adapter.watchlists.WatchlistsViewModel;

/**
 * The View for when the user views a movie.
 */
public class MovieView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Movie Information";

    private final MovieViewModel movieViewModel;
    private MovieController movieController;

    private final JButton backButton;
    private final JButton homeButton;
    private final JButton watchedButton;
    private final JButton addToListButton;
    private final JButton userReviewsButton;

    public MovieView(MovieViewModel movieViewModel) {
        this.movieViewModel = movieViewModel;
        this.movieViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(MovieViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.backButton = new JButton(MovieViewModel.BACK_LABEL);
        this.homeButton = new JButton(MovieViewModel.HOME_LABEL);

        final JPanel topButtons = new JPanel();
        topButtons.setLayout(new BorderLayout());
        topButtons.add(backButton, BorderLayout.WEST);
        topButtons.add(homeButton, BorderLayout.EAST);

        // TODO: show info about movie via API via label - IDK how

        this.watchedButton = new JButton(MovieViewModel.PWL_LABEL);
        this.addToListButton = new JButton(MovieViewModel.ADD_TO_LIST_LABEL);
        this.userReviewsButton = new JButton(MovieViewModel.USER_REVIEWS_LABEL);

        final JPanel bottomButtons = new JPanel();
        bottomButtons.add(watchedButton);
        bottomButtons.add(addToListButton);
        bottomButtons.add(userReviewsButton);

        // TODO: add listeners here for the above buttons

        addToListButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addToListButton)) {
                            addToListPopUpView();
                        }
                    }
                }
        );
    }

    /**
     * View for Add to List Pop-Up Window.
     */
    private void addToListPopUpView() {
        final JPanel panel = new JPanel(new BorderLayout());

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        final List<JButton> buttonList = new ArrayList<>();

        // TODO: find a way to access user!
        final List<Watchlist> watchlists = user.getWatchlists();
        for (Watchlist watchlist:watchlists) {
            final String listName = watchlist.getName;
            final JButton list = new JButton(listName);
            // TODO: movie title not added yet
            if (watchlist.movieExists(movieTitle)) {
                list.setEnabled(false);
            }
            buttonPanel.add(list);
            buttonList.add(list);
        }

        for (JButton button:buttonList){
            button.addActionListener(
                    new ActionListener() {}
            );
        }

        // cancel button
        final JButton cancelButton = new JButton("Cancel");

        // Add items to panel
        // panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(cancelButton, BorderLayout.SOUTH);

        JOptionPane.showOptionDialog(this, panel, "My Lists", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new Object[]{}, null);

        // TODO: implement addActionListener for lists (for-loop) and cancelButton.addActionListener
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
