package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import interface_adapter.ViewManagerModel;
import interface_adapter.show_books.ShowBooksViewModel;

/**
 * The Show books viewclass represents the GUI for users to see books in bookclubs.
 */
public class ShowBooksView extends JPanel implements PropertyChangeListener, ActionListener {

    private static final String VIEW_NAME = "Books";

    private final JTextArea welcome = new JTextArea("Books List");
    private final JTextArea instructions = new JTextArea("You can see all the books in this book club");
    private final DefaultListModel<String> bookListModel = new DefaultListModel<>();
    private final JList<String> bookList = new JList<>(bookListModel);

    private final JButton backButton = new JButton("Back");

    private final ShowBooksViewModel showBooksViewModel;
    private final ViewManagerModel viewManagerModel;

    private final float font = 16f;
    private final float width = 14f;

    public ShowBooksView(ShowBooksViewModel showBooksViewModel, ViewManagerModel viewManagerModel) {
        this.showBooksViewModel = showBooksViewModel;
        this.viewManagerModel = viewManagerModel;
        showBooksViewModel.addPropertyChangeListener(this);

        setupU();
    }

    /**
     * Sets up the UI components and layout for the show books view.
     */
    private void setupU() {
        this.setLayout(new BorderLayout());

        // Create top panel for title and instructions
        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Configure welcome text
        welcome.setEditable(false);
        welcome.setWrapStyleWord(true);
        welcome.setLineWrap(true);
        welcome.setBackground(this.getBackground());
        welcome.setFont(welcome.getFont().deriveFont(font));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(welcome);

        // Configure instructions text
        instructions.setEditable(false);
        instructions.setWrapStyleWord(true);
        instructions.setLineWrap(true);
        instructions.setBackground(this.getBackground());
        instructions.setFont(instructions.getFont().deriveFont(font));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(instructions);

        // Add top panel to the top of the layout
        this.add(topPanel, BorderLayout.NORTH);

        // Configure book list with a scroll pane
        final JScrollPane scrollPane = new JScrollPane(bookList);
        this.add(scrollPane, BorderLayout.CENTER);

        // Create bottom panel for buttons
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(backButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners for buttons
        backButton.addActionListener(this);
    }

    /**
     * Populates the list with books from the view model's state.
     */
    private void populateBooksList() {
        bookListModel.clear();
        final List<String> books = showBooksViewModel.getState().getBookList();
        for (String book : books) {
            bookListModel.addElement(book);
        }
    }

    public String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            viewManagerModel.setState("my clubs");
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            populateBooksList();
        }
    }
}
