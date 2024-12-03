package view;

import entity.User;
import entity.Watchlist;
import interface_adapter.watchlist.WatchlistController;
import interface_adapter.watchlist.WatchlistViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the screen showing a content of a watchlist.
 */
public class WatchlistView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName;

    private final WatchlistViewModel watchlistViewModel;
    private WatchlistController watchlistController;

    private JLabel title;
    private JPanel topLine;
    private JButton addMovie;
    private JPanel movieButtons;

    public WatchlistView(WatchlistViewModel watchlistViewModel) {
        this.watchlistViewModel = watchlistViewModel;
        this.viewName = watchlistViewModel.getViewName();
        watchlistViewModel.addPropertyChangeListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.topLine = new JPanel();
        topLine.setLayout(new BoxLayout(topLine, BoxLayout.X_AXIS));
        topLine.setPreferredSize(new Dimension(400, 50));

        final JButton back = new JButton(watchlistViewModel.BACK_LABEL);
        back.setAlignmentX(Component.LEFT_ALIGNMENT);
        back.addActionListener(

                evt -> watchlistController.backToWatchlistsView(this.watchlistViewModel.getState().getCurrentUser())
        );
        topLine.add(back);

        this.title = new JLabel(watchlistViewModel.getState().getWatchlistName());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        topLine.add(title);
        this.add(topLine);

        final JButton home = new JButton(watchlistViewModel.HOME_LABEL);
        home.setAlignmentX(Component.RIGHT_ALIGNMENT);
        home.addActionListener(
                evt -> watchlistController.switchToHomeView()
        );
        topLine.add(home);

        this.addMovie = new JButton(watchlistViewModel.ADD_MOVIE_LABEL);
        addMovie.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(addMovie);
        addMovie.addActionListener(
                evt -> watchlistController.switchToMovieSearchView(watchlistViewModel.getState().getCurrentUser())
        );

        this.movieButtons = new JPanel();
        this.movieButtons.setLayout(new BoxLayout(this.movieButtons, BoxLayout.Y_AXIS));
        this.add(this.movieButtons);
        this.setPreferredSize(new Dimension(400, 300));
        final Color backcolor = new Color(255, 255, 255);
        this.setBackground(backcolor);

    }

    private void updateWatchlist() {
        this.title.setText(this.watchlistViewModel.getState().getWatchlistName());
        while (this.movieButtons.getComponentCount() > 0) {
            this.movieButtons.remove(this.movieButtons.getComponentCount() - 1);
        }
        final Watchlist movies = watchlistViewModel.getState().getWatchlist();
        for (int i = 0; i < movies.size(); i++) {
            final JPanel buttons = new JPanel();
            buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
            final JButton movie = new JButton(movies.getMovie(i).getTitle());
            final int ind = i;
            movie.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(movie)) {
//                            TODO: ask Rhea if she already has controller that can do this
                        }
                    }
            );
            buttons.add(movie);
            final JButton remove = new JButton(watchlistViewModel.REMOVE_LABEL);
            remove.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(remove)) {
//                            TODO: write controller
//                            final String currentUser = watchlistViewModel.getState().getCurrentUser();
//                            final int watchlistIndex = watchlistViewModel.getState().getWatchlistIndex();
//
//                            removeMovieController.execute(currentUser, watchlistIndex, ind);
                        }
                    }
            );
            buttons.add(remove);
            this.movieButtons.add(buttons);
            this.revalidate();
        }
    }

    /**
     * View for Create a New List Pop-Up Window.
     * @return 0 (yes) or  1 (no) for which button was clicked
     */
//    private int createNewListPopUpView() {
//        // final int maxChar = 75;
//        final JPanel panel = new JPanel();
//
//        // Adjusting panel size
//        panel.setPreferredSize(new Dimension(500, 200));
//
//        final JPanel textPanel = new JPanel(new BorderLayout());
//        // Text field for naming new list
//        final JLabel enterNameLabel = new JLabel("Enter List Name:");
//        // final JTextField listNameField = new JTextField(40);
//
//        addListNameListener();
//        // not functioning currently, not a today problem
//        final int x = listNameField.getText().length();
//        final JLabel characterLimitLabel = new JLabel("Character Limit: " + x + "/100");
//
//        // Add text field components to panel
//        textPanel.add(enterNameLabel, BorderLayout.NORTH);
//        textPanel.add(listNameField, BorderLayout.CENTER);
//        textPanel.add(characterLimitLabel, BorderLayout.SOUTH);
//
//        // Add components to the panel
//        panel.add(textPanel);
//
//        // Create a custom dialog with our panel
//        return JOptionPane.showOptionDialog(this, panel, "Create a New List",
//                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
//                null, new Object[]{"Create List", "Cancel"}, null);
//    }

//    private void addListNameListener() {
//        listNameField.getDocument().addDocumentListener(new DocumentListener() {
//            private void documentListenerHelper() {
//                final WatchlistsState currentState = watchlistsViewModel.getState();
//                currentState.setListName(listNameField.getText());
//                watchlistsViewModel.setState(currentState);
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//        });
//    }

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

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.updateWatchlist();
    }
}
