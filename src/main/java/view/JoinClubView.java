package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import interface_adapter.ViewManagerModel;
import interface_adapter.join_club.JoinClubController;
import interface_adapter.join_club.JoinClubViewModel;

/**
 * The Join_ClubView class represents the GUI for users to join a book club.
 * It displays available book clubs, handles user interactions, and communicates
 * with the JoinClubController to execute user actions.
 */
public final class JoinClubView extends JPanel implements PropertyChangeListener, ActionListener {

    private static final String VIEW_NAME = "JoinClub";

    private final JTextArea welcome = new JTextArea("Join A Club");
    private final JTextArea instructions = new JTextArea("Select a book club from the drop-down menu.");
    private final JComboBox<String> bookClubListComboBox = new JComboBox<>();
    private final JButton backButton = new JButton("Back");
    private final JButton joinClubButton = new JButton("Join Club");

    private final JoinClubViewModel joinClubViewModel;
    private final ViewManagerModel viewManagerModel;

    private JoinClubController joinClubController;

    private final float font = 16f;
    private final float width = 14f;

    /**
     * Constructs a new Join_ClubView instance.
     *
     * @param joinClubViewModel the ViewModel that holds the current state of the join club functionality
     * @param viewManagerModel  the ViewManagerModel to manage view transitions
     */
    public JoinClubView(final JoinClubViewModel joinClubViewModel, final ViewManagerModel viewManagerModel) {
        this.joinClubViewModel = joinClubViewModel;
        this.viewManagerModel = viewManagerModel;
        this.joinClubViewModel.addPropertyChangeListener(this);
        setupU();
    }

    /**
     * Sets up the UI components and layout for the join club view.
     */
    private void setupU() {
        this.setLayout(new BorderLayout());

        // Top panel for welcome and instructions
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Configure welcome text area
        welcome.setEditable(false);
        welcome.setWrapStyleWord(true);
        welcome.setLineWrap(true);
        welcome.setFont(welcome.getFont().deriveFont(font));
        welcome.setBackground(this.getBackground());
        topPanel.add(welcome);

        // Configure instructions text area
        instructions.setEditable(false);
        instructions.setWrapStyleWord(true);
        instructions.setLineWrap(true);
        instructions.setFont(instructions.getFont().deriveFont(width));
        instructions.setBackground(this.getBackground());
        topPanel.add(instructions);

        this.add(topPanel, BorderLayout.NORTH);

        // Center panel for combo box
        final JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(bookClubListComboBox);
        this.add(centerPanel, BorderLayout.CENTER);

        // Bottom panel for buttons
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);
        bottomPanel.add(joinClubButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        backButton.addActionListener(this);
        joinClubButton.addActionListener(this);
    }

    /**
     * Populates the book club list dropdown menu with available book clubs.
     */
    private void populateBookClubList() {
        bookClubListComboBox.removeAllItems();
        final List<String> bookClubList = joinClubViewModel.getState().getBookClubList();
        for (String bookClub : bookClubList) {
            bookClubListComboBox.addItem(bookClub);
        }
    }

    /**
     * Gets the name of this view.
     *
     * @return the view name
     */
    public String getViewName() {
        return VIEW_NAME;
    }

    /**
     * Handles property change events. Updates the dropdown list when the state changes.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            populateBookClubList();
        }
        else if ("error".equals(evt.getPropertyName())) {
            final String error = joinClubViewModel.getState().getErrorMessage();
            JOptionPane.showMessageDialog(this, error);
        }
        else if ("success".equals(evt.getPropertyName())) {
            final String successMessage = joinClubViewModel.getState().getSucceesMessage();

            // Custom success message with OK button using showOptionDialog
            final int option = JOptionPane.showOptionDialog(this,
                    successMessage,
                    "Success",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new Object[]{"OK"},
                    "OK");

            // Check if OK was clicked
            if (option == 0) {
                joinClubController.switchToLoggedInView();
            }
        }
    }

    /**
     * Handles user actions on the buttons (Join Club and Back).
     *
     * @param e the action event
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == joinClubButton) {
            final String selectedOption = (String) bookClubListComboBox.getSelectedItem();
            if (selectedOption != null) {
                final String[] parts = selectedOption.split(" - ");
                final String clubName = parts[0];
                joinClubController.execute(joinClubViewModel.getState().getUsername(), clubName);
            }
            else {
                JOptionPane.showMessageDialog(this, "Please select a club to join.");
            }
        }
        if (e.getSource() == backButton) {
            viewManagerModel.setState("logged in");
            viewManagerModel.firePropertyChanged();
        }
    }

    /**
     * Sets the controller for this view.
     *
     * @param joinClubController the controller to handle user actions
     */
    public void setJoinClubController(final JoinClubController joinClubController) {
        this.joinClubController = joinClubController;
    }
}
