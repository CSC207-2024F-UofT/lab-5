package interface_adapter.search_results;

import java.util.List;

import entity.CommonMovie;

/**
 * The state for the Search Results View Model.
 */
public class SearchResultsState {
    private String searchTitle;
    private String searchError;
    private List<String> movieTitles;
    private List<String> posterPaths;
    private List<CommonMovie> results;
    private String username;

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public String getSearchError() {
        return searchError;
    }

    public void setSearchResultsError(String errorMessage) {
        this.searchError = errorMessage;
    }

    public List<String> getMovieTitles() {
        return movieTitles;
    }

    public void setMovieTitles(List<String> movieTitles) {
        this.movieTitles = movieTitles;
    }

    public List<String> getPosterPaths() {
        return posterPaths;
    }

    public void setPosterPaths(List<String> posterPaths) {
        this.posterPaths = posterPaths;
    }

    public void setResults(List<CommonMovie> results) {
        this.results = results;
    }

    public List<CommonMovie> getResults() {
        return results;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
