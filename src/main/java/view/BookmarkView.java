package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.bookmark.BookmarkController;
import interface_adapter.bookmark.BookmarkState;
import interface_adapter.bookmark.BookmarkViewModel;

/**
 * The View for the Bookmark Use Case.
 */
public class BookmarkView extends JPanel implements PropertyChangeListener {

    private final String viewName = "bookmark";
    private final BookmarkViewModel bookmarkViewModel;
    private BookmarkController bookmarkController;

    private final JButton addBookmarkButton;
    private final JButton removeBookmarkButton;
    private final JButton backButton;

    public BookmarkView(BookmarkViewModel bookmarkViewModel) {
        this.bookmarkViewModel = bookmarkViewModel;
        this.bookmarkViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(BookmarkViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        addBookmarkButton = new JButton(BookmarkViewModel.ADD_BOOKMARK_BUTTON_LABEL);
        buttons.add(addBookmarkButton);
        removeBookmarkButton = new JButton(BookmarkViewModel.REMOVE_BOOKMARK_BUTTON_LABEL);
        buttons.add(removeBookmarkButton);
        backButton = new JButton(BookmarkViewModel.BACK_BUTTON_LABEL);
        buttons.add(backButton);

        addBookmarkButton.addActionListener(evt -> {
            final BookmarkState currentState = bookmarkViewModel.getState();
            if (currentState != null && currentState.getTranslationId() != null) {
                bookmarkController.addBookmark(currentState.getUsername(), currentState.getTranslationId());
            }
        });

        removeBookmarkButton.addActionListener(evt -> {
            final BookmarkState currentState = bookmarkViewModel.getState();
            if (currentState != null && currentState.getTranslationId() != null) {
                bookmarkController.removeBookmark(currentState.getUsername(), currentState.getTranslationId());
            }
        });
          
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final BookmarkState state = (BookmarkState) evt.getNewValue();
        if (state != null) {
            if (state.getStatusMessage() != null && !state.getStatusMessage().isEmpty()) {
                JOptionPane.showMessageDialog(this, state.getStatusMessage());
            }
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setBookmarkController(BookmarkController controller) {
        this.bookmarkController = controller;
    }
}
