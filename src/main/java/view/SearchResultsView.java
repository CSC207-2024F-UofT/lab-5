package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import java.util.List;

import entity.CommonMovie;
import interface_adapter.search_results.SearchResultsController;
import interface_adapter.search_results.SearchResultsState;
import interface_adapter.search_results.SearchResultsViewModel;

/**
 * The View for when the user views a searches for a movie.
 */
public class SearchResultsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Search Results";

    private final SearchResultsViewModel searchResultsViewModel;
    private SearchResultsController searchResultsController;

    private final JButton home;
    private final JButton enter;

    private final JPanel results;

    private final JTextField searchInputField = new JTextField(50);
    private final JLabel searchResultsErrorField = new JLabel();

    public SearchResultsView(SearchResultsViewModel searchResultsViewModel) {
        this.searchResultsViewModel = searchResultsViewModel;

        // Register as an observer
        this.searchResultsViewModel.addPropertyChangeListener(this);

        // Set the main layout to BorderLayout
        this.setLayout(new BorderLayout());

        // creates the home button and its functionality
        this.home = new JButton(SearchResultsViewModel.HOME_LABEL);
        home.addActionListener(
                evt -> searchResultsController.switchToHomeView()
        );

        // Home button panel on the left
        final JPanel homeButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        homeButton.add(home);
        this.add(homeButton, BorderLayout.WEST);

        // create title on centre top
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel title = new JLabel(searchResultsViewModel.TITLE_LABEL);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 18));
        titlePanel.add(title, BorderLayout.NORTH);

        // creates the enter button, which has its functionality in actionPerformed
        this.enter = new JButton(SearchResultsViewModel.ENTER_LABEL);
        enter.addActionListener(evt -> {
            final String searchText = searchInputField.getText();
            searchResultsController.execute(searchText);
        });

        // create the search bar - contains the search label, the input field, and the enter button
        final JPanel searchBar = new JPanel();
        searchBar.add(new JLabel(searchResultsViewModel.SEARCH_LABEL));
        searchBar.add(searchInputField);
        searchBar.add(enter);

        // for each movie, create new panel and add it to the results panel
        this.results = new JPanel();
        final List<String> movies = searchResultsViewModel.getState().getMovieTitles();
        final List<String> posterPaths = searchResultsViewModel.getState().getPosterPaths();
        final List<CommonMovie> searchResults = searchResultsViewModel.getState().getResults();

        if (movies != null) {
            for (int i = 0; i < movies.size(); i++) {
                results.add(createMoviePanel(searchResults.get(i), posterPaths.get(i)));
            }
        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(homeButton, BorderLayout.WEST);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(searchBar);
        this.add(results);
    }

    /**
     * Create a panel for an individual movie.
     * @param searchResult the common movie that was searched
     * @param posterPath the poster path of the given movie
     */
    private JPanel createMoviePanel(CommonMovie searchResult, String posterPath) {
        final JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new BorderLayout(10, 10));

        // Load and display the movie poster
        final ImageIcon posterIcon = new ImageIcon(posterPath);
        final JLabel posterLabel = new JLabel(posterIcon);
        posterLabel.setPreferredSize(new Dimension(100, 150));

        // Create and add the movie title
        final JLabel titleLabel = new JLabel(searchResult.getTitle());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add "See More" button
        final JButton seeMoreButton = new JButton(searchResultsViewModel.SEE_MORE_LABEL);
        final String user = searchResultsViewModel.getState().getUsername();

        seeMoreButton.addActionListener(
                evt -> searchResultsController.switchToMovieView(user, searchResult)
        );

        // Arrange components
        final JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.add(titleLabel, BorderLayout.NORTH);
        textPanel.add(seeMoreButton, BorderLayout.SOUTH);

        moviePanel.add(posterLabel, BorderLayout.WEST);
        moviePanel.add(textPanel, BorderLayout.CENTER);

        return moviePanel;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == enter) {
            final String query = searchInputField.getText();
            searchResultsController.execute(query);
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        final List<String> titles = searchResultsViewModel.getState().getMovieTitles();
        final List<String> posters = searchResultsViewModel.getState().getPosterPaths();
        final List<CommonMovie> searchedResults = searchResultsViewModel.getState().getResults();

        results.removeAll();
        if (searchedResults == null) {
            // Trigger the failure view if no results are found
            prepareFailView("No movies found for the given search query. Please try again.");
        } else {
            // Add the new results to the view
            for (int i = 0; i < titles.size(); i++) {
                final JPanel moviePanel = createMoviePanel(searchedResults.get(i), posters.get(i));
                results.add(moviePanel);
            }
        }
        results.revalidate();
        results.repaint();

    }

    /**
     * Display a failure message if no movies are found.
     * @param errorMessage The error message to display in the pop-up.
     */
    public void prepareFailView(String errorMessage) {
        // Display the error message using a JOptionPane
        JOptionPane.showMessageDialog(
                this, // Parent component
                errorMessage, // Message to display
                "Search Error", // Title of the pop-up
                JOptionPane.ERROR_MESSAGE // Type of pop-up (error)
        );
    }

    private void setFields(SearchResultsState state) {
        searchInputField.setText(state.getSearchTitle());
    }

    public String getViewName() {
        return viewName;
    }

    public void setSearchResultsController(SearchResultsController searchResultsController) {
        this.searchResultsController = searchResultsController;
    }
}
