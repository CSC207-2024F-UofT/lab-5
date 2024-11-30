package view;

import entity.User;
import entity.UserWatchlist;
import interface_adapter.create_watchlist.CreateWatchlistController;
import interface_adapter.watchlists.WatchlistsController;
import interface_adapter.watchlists.WatchlistsState;
import interface_adapter.watchlists.WatchlistsViewModel;
import interface_adapter.watchlists.delete.DeleteWatchlistController;
import interface_adapter.watchlists.rename.RenameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The View for the screen showing a list of watchlists.
 */
public class WatchlistsView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName;

    private final WatchlistsViewModel watchlistsViewModel;
    private WatchlistsController watchlistsController;

    private CreateWatchlistController createWatchlistController;
    private RenameController renameController;
    private DeleteWatchlistController deleteController;

    private JPanel topLine;
    private JButton createWatchlist;
    private JButton pwl;
    private JPanel watchlistButtons;

    private int x = 0;
    private String currentListName = "";

    private final JTextField listNameField = new JTextField();

    public WatchlistsView(WatchlistsViewModel watchlistsViewModel) {
        this.watchlistsViewModel = watchlistsViewModel;
        this.viewName = watchlistsViewModel.getViewName();
        watchlistsViewModel.addPropertyChangeListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.topLine = new JPanel();
        topLine.setLayout(new BoxLayout(topLine, BoxLayout.X_AXIS));
        topLine.setPreferredSize(new Dimension(400, 50));

        final JButton home = new JButton(watchlistsViewModel.HOME_LABEL);
        home.setAlignmentX(Component.LEFT_ALIGNMENT);
        home.addActionListener(
                evt -> watchlistsController.switchToHomeView()
        );
        topLine.add(home);

        final JLabel title = new JLabel(watchlistsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        topLine.add(title);
        this.add(topLine);

        this.createWatchlist = new JButton(watchlistsViewModel.CREATE_LIST_LABEL);

        createWatchlist.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(createWatchlist);
        createWatchlist.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createWatchlist)) {
                            createNewListPopUpView();
                        }
                    }
                }
        );
        this.watchlistButtons = new JPanel();
        this.watchlistButtons.setLayout(new BoxLayout(this.watchlistButtons, BoxLayout.Y_AXIS));
        this.pwl = new JButton(watchlistsViewModel.PWL_LABEL);
        this.pwl.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.pwl.addActionListener(
                evt -> {
                    final User currentUser = watchlistsViewModel.getState().getCurrentUser();

                    watchlistsController.goToPWL(currentUser);
                }
        );
        this.watchlistButtons.add(this.pwl);
        this.add(this.watchlistButtons);
        this.setPreferredSize(new Dimension(400, 300));
        final Color backcolor = new Color(255, 255, 255);
        this.setBackground(backcolor);
    }

    private void updateWatchlists() {
        while (this.watchlistButtons.getComponentCount() > 1) {
            this.watchlistButtons.remove(this.watchlistButtons.getComponentCount() - 1);
        }
        final List<UserWatchlist> watchlists = watchlistsViewModel.getState().getCurrentUser().getWatchlists();
        System.out.println(watchlists.size());
        for (int i = 0; i < watchlists.size(); i++) {
            final JPanel buttons = new JPanel();
            buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
            final JButton watchlist = new JButton(watchlists.get(i).getListName());
            final int ind = i;
            watchlist.addActionListener(
                    evt -> {
                        final User currentUser = watchlistsViewModel.getState().getCurrentUser();

                        watchlistsController.goToWatchlist(currentUser,
                                ind);

                    }
            );
            buttons.add(watchlist);
            final JButton rename = new JButton(watchlistsViewModel.RENAME_LABEL);
            rename.addActionListener(
                    evt -> renameListPopUpView(watchlists.get(ind).getListName(), ind)
            );
            buttons.add(rename);
            final JButton delete = new JButton(watchlistsViewModel.DELETE_LABEL);
            delete.addActionListener(
                    evt -> deleteListPopUpView(watchlists.get(ind).getListName(), ind)
            );
            buttons.add(delete);
            this.watchlistButtons.add(buttons);
        }
        this.revalidate();
    }

    /**
     * View for Create a New List Pop-Up Window.
     */
    private void createNewListPopUpView() {
        // final int maxChar = 75;
        final JPanel panel = new JPanel();

        // Adjusting panel size
        panel.setPreferredSize(new Dimension(500, 200));

        final JPanel textPanel = new JPanel(new BorderLayout());
        // Text field for naming new list
        final JLabel enterNameLabel = new JLabel("Enter List Name:");
        // final JTextField listNameField = new JTextField(40);

        final int maxChar = 100;
        final JLabel characterLimitLabel = new JLabel("Character Limit: " + x + "/" + maxChar);

        listNameField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateCharacterCount(1);
                recordText();
            }

            public void removeUpdate(DocumentEvent e) {
                updateCharacterCount(-1);
                recordText();
            }

            public void changedUpdate(DocumentEvent e) {
                /* Not needed for plain text fields */
            }

            private void updateCharacterCount(int increment) {
                x += increment;
                characterLimitLabel.setText("Character Limit: " + x + "/" + maxChar);
            }

            private void recordText() {
                // Record the current text entered in listNameField
                currentListName = listNameField.getText();
            }
        });

        // Add text field components to panel
        textPanel.add(enterNameLabel, BorderLayout.NORTH);
        textPanel.add(listNameField, BorderLayout.CENTER);
        textPanel.add(characterLimitLabel, BorderLayout.SOUTH);

        // Buttons
        final JButton createWatchlistButton = new JButton("Create Watchlist");
        final JButton cancel = new JButton("Cancel");

        final JPanel buttons = new JPanel();

        // Action Listener for buttons
        createWatchlistButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createWatchlistButton)) {
                            final WatchlistsState currentState = watchlistsViewModel.getState();
                            final User currUser = currentState.getCurrentUser();
                            if (!currentListName.isEmpty()) {
                                createWatchlistController.execute(currUser, currentListName);
                            }
                            else {
                                JOptionPane.showMessageDialog(panel, currentState.getEmptyListNameError());
                            }
                            listNameField.setText("");
                            x = 0;
                            SwingUtilities.getWindowAncestor(createWatchlistButton).dispose();
                            // System.out.println(currUser.getWatchlists()); // testing purposes
                        }
                    }
                }
        );

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)) {
                    listNameField.setText("");
                    x = 0;
                    SwingUtilities.getWindowAncestor(cancel).dispose();
                }
            }
        });

        buttons.add(cancel);
        buttons.add(createWatchlistButton);

        // Add components to the panel
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        // Create a custom dialog with our panel
        JOptionPane.showOptionDialog(this, panel, "Create a New List",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, new Object[]{}, null);
    }

    /**
     * View for Create a New List Pop-Up Window.
     */
    private void renameListPopUpView(String oldName, int ind) {
        // final int maxChar = 75;
        final JPanel panel = new JPanel();

        // Adjusting panel size
        panel.setPreferredSize(new Dimension(500, 200));

        final JPanel textPanel = new JPanel(new BorderLayout());
        // Text field for naming new list
        final JLabel enterNameLabel = new JLabel("Enter a new name for a " + oldName + " watchlist:");
        // final JTextField listNameField = new JTextField(40);

        final int maxChar = 100;
        final JLabel characterLimitLabel = new JLabel("Character Limit: " + x + "/" + maxChar);

        listNameField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateCharacterCount(1);
                recordText();
            }

            public void removeUpdate(DocumentEvent e) {
                updateCharacterCount(-1);
                recordText();
            }

            public void changedUpdate(DocumentEvent e) {
                /* Not needed for plain text fields */
            }

            private void updateCharacterCount(int increment) {
                x += increment;
                characterLimitLabel.setText("Character Limit: " + x + "/" + maxChar);
            }

            private void recordText() {
                // Record the current text entered in listNameField
                currentListName = listNameField.getText();
            }
        });

        // Add text field components to panel
        textPanel.add(enterNameLabel, BorderLayout.NORTH);
        textPanel.add(listNameField, BorderLayout.CENTER);
        textPanel.add(characterLimitLabel, BorderLayout.SOUTH);

        // Buttons
        final JButton renameButton = new JButton("Rename");
        final JButton cancel = new JButton("Cancel");

        final JPanel buttons = new JPanel();

        // Action Listener for buttons
        renameButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (!currentListName.isEmpty()) {
                            renameController.execute(watchlistsViewModel.getState().getCurrentUser(),
                                    ind, currentListName);
                            listNameField.setText("");
                            x = 0;
                            SwingUtilities.getWindowAncestor(renameButton).dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(panel, watchlistsViewModel.getState().getEmptyListNameError());
                        }
                    }
                }
        );

        cancel.addActionListener(evt -> {
            if (evt.getSource().equals(cancel)) {
                listNameField.setText("");
                x = 0;
                SwingUtilities.getWindowAncestor(cancel).dispose();
            }
        });

        buttons.add(cancel);
        buttons.add(renameButton);

        // Add components to the panel
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        // Create a custom dialog with our panel
        JOptionPane.showOptionDialog(this, panel, "Rename a Watchlist",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, new Object[]{}, null);
    }

    /**
     * View for Create a New List Pop-Up Window.
     */
    private void deleteListPopUpView(String listName, int ind) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Adjusting panel size
        panel.setPreferredSize(new Dimension(400, 100));

        final JLabel confirmLabel = new JLabel("Are you sure you want to delete watchlist " + listName + "?");

        // Buttons
        final JPanel buttons = new JPanel(new BorderLayout());
        final JButton deleteButton = new JButton("Yes");
        final JButton cancel = new JButton("No");

        // Action Listener for buttons
        deleteButton.addActionListener(
                evt -> {
                    deleteController.execute(watchlistsViewModel.getState().getCurrentUser(), ind);
                    SwingUtilities.getWindowAncestor(deleteButton).dispose();
                }
        );

        cancel.addActionListener(evt -> {
            if (evt.getSource().equals(cancel)) {
                SwingUtilities.getWindowAncestor(cancel).dispose();
            }
        });

        cancel.setPreferredSize(new Dimension(60, 60));
        deleteButton.setPreferredSize(new Dimension(70, 60));
        buttons.add(cancel, BorderLayout.WEST);
        buttons.add(deleteButton, BorderLayout.EAST);

        // Add components to the panel
        panel.add(confirmLabel);
        panel.add(Box.createRigidArea(new Dimension(5, 20)));
        panel.add(buttons);

        // Create a custom dialog with our panel
        JOptionPane.showOptionDialog(this, panel, "Delete a Watchlist",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, new Object[]{}, null);
    }

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

    public void setCreateWatchlistController(CreateWatchlistController controller) {
        this.createWatchlistController = controller;
    }

    public void setRenameController(RenameController controller) {
        this.renameController = controller;
    }

    public void setDeleteController(DeleteWatchlistController controller) {
        this.deleteController = controller;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.updateWatchlists();
    }
}
