package view;

import entity.User;
import interface_adapter.signup.SignupState;
import interface_adapter.watchlists.WatchlistsController;
import interface_adapter.watchlists.WatchlistsState;
import interface_adapter.watchlists.WatchlistsViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The View for the watchlists screen.
 */
public class WatchlistsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "My Watchlists";

    private final WatchlistsViewModel watchlistsViewModel;
    private WatchlistsController watchlistsController;

    private final JButton home;
    private final JButton createWatchlist;
    private final JButton pwl;
    private List watchlistButtons;

    private final JTextField listNameField = new JTextField(40);

    public WatchlistsView(WatchlistsViewModel watchlistsViewModel) {
        this.watchlistsViewModel = watchlistsViewModel;
        watchlistsViewModel.addPropertyChangeListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel(watchlistsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);

        this.home = new JButton(watchlistsViewModel.HOME_LABEL);
        home.setAlignmentX(Component.RIGHT_ALIGNMENT);
        home.addActionListener(
                evt -> watchlistsController.switchToHomeView()
        );
        this.add(home);
        this.createWatchlist = new JButton(watchlistsViewModel.CREATE_LIST_LABEL);
        //        createWatchlist.addActionListener(
        //                evt -> createWatchlistController.execute()
        //        );
        createWatchlist.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(createWatchlist);
        this.pwl = new JButton(watchlistsViewModel.PWL_LABEL);
        pwl.setAlignmentX(Component.CENTER_ALIGNMENT);
        pwl.addActionListener(
                evt -> {
                    final User currentUser = watchlistsViewModel.getState().getCurrentUser();

                    watchlistsController.goToPWL(currentUser);
                }
        );
        createWatchlist.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createWatchlist)) {
                            createNewListPopUpView();
                            // TODO: if (result == JOptionPane.YES_OPTION) add new list to user's existing lists
                        }
                    }
                }
        );
        this.add(pwl);
//        this.updateWatchlists();

    }

    private void updateWatchlists() {
        final List<String> watchlists = watchlistsViewModel.getState().getCurrentUser().getWatchlists();
        this.watchlistButtons = new ArrayList<JPanel>();
        for (int i = 0; i < watchlists.size(); i++) {
            final JPanel buttons = new JPanel();
            final JButton watchlist = new JButton(watchlists.get(i));
            final int ind = i;
            watchlist.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(watchlist)) {
                            final User currentUser = watchlistsViewModel.getState().getCurrentUser();

                            watchlistsController.goToWatchlist(currentUser,
                                    ind);
                        }
                    }
            );
            buttons.add(watchlist);
            final JButton rename = new JButton(watchlistsViewModel.RENAME_LABEL);
            //            rename.addActionListener(
            //                    evt -> {
            //                        if (evt.getSource().equals(rename)) {
            //                            final User currentUser = watchlistsViewModel.getState().getCurrentUser();
            //
            //                            renameWatchlistController.execute(currentUser, ind);
            //                        }
            //                    }
            //            );
            buttons.add(rename);
            final JButton delete = new JButton(watchlistsViewModel.DELETE_LABEL);
            //            delete.addActionListener(
            //                    evt -> {
            //                        if (evt.getSource().equals(delete)) {
            //                            final User currentUser = watchlistsViewModel.getState().getCurrentUser();
            //
            //                            deleteWatchlistController.execute(currentUser, ind);
            //                        }
            //                    }
            //            );
            buttons.add(delete);
            this.watchlistButtons.add(buttons);
        }
    }

    /**
     * View for Create a New List Pop-Up Window.
     * @return 0 (yes) or  1 (no) for which button was clicked
     */
    private int createNewListPopUpView() {
        // final int maxChar = 75;
        final JPanel panel = new JPanel();

        // Adjusting panel size
        panel.setPreferredSize(new Dimension(500, 200));

        final JPanel textPanel = new JPanel(new BorderLayout());
        // Text field for naming new list
        final JLabel enterNameLabel = new JLabel("Enter List Name:");
        // final JTextField listNameField = new JTextField(40);

        // addListNameListener();
        // not functioning currently, not a today problem
        final int x = listNameField.getText().length();
        final JLabel characterLimitLabel = new JLabel("Character Limit: " + x + "/100");

        // Add text field components to panel
        textPanel.add(enterNameLabel, BorderLayout.NORTH);
        textPanel.add(listNameField, BorderLayout.CENTER);
        textPanel.add(characterLimitLabel, BorderLayout.SOUTH);

        // Add components to the panel
        panel.add(textPanel);

        // Create a custom dialog with our panel
        return JOptionPane.showOptionDialog(this, panel, "Create a New List",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, new Object[]{"Create List", "Cancel"}, null);
    }

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

    public void setWatchlistsController(WatchlistsController controller) {
        this.watchlistsController = controller;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
