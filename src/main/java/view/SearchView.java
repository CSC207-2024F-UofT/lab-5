package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

/**
 * The View for when the user is searching for a book.
 *
 */
public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "searching";
    private final SearchViewModel searchViewModel;

    private final JTextField queryInputField = new JTextField(30);
    private final JLabel queryLabel = new JLabel();

    private final JButton searchButton;

    private SearchController searchController;
    private final JLabel messageLabel = new JLabel();

    public SearchView(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
        this.searchViewModel.addPropertyChangeListener(this);

        final LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel("Search Query"), queryInputField);

        final JPanel button = new JPanel();
        searchButton = new JButton("Search");
        button.add(searchButton);

        searchButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(searchButton)) {
                            final SearchState currentState = searchViewModel.getState();

                            searchController.startSearch(currentState.getQuery());
                        }
                    }
                }
        );

        queryInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SearchState currentState = searchViewModel.getState();
                currentState.setQuery(queryInputField.getText());
                searchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        this.add(searchInfo);
        this.add(button);

    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SearchState state = (SearchState) evt.getNewValue();
        updateFields(state);
        messageLabel.setText(state.getErrorMessage());
    }

    private void updateFields(SearchState state) {
        queryInputField.setText(state.getQuery());
    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }
}

