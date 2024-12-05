package use_case.search_results;

import java.util.List;

import entity.CommonMovie;

/**
 * Output Data for the Search Results Use Case.
 */
public class SearchResultsOutputData {

    private final String searchText;
    private final List<String> movieTitles;
    private final List<String> posterPaths;
    private final List<CommonMovie> results;

    public SearchResultsOutputData(String searchText, List<String> movieTitles,
                                   List<String> posterPaths, List<CommonMovie> results) {
        this.searchText = searchText;
        this.movieTitles = movieTitles;
        this.posterPaths = posterPaths;
        this.results = results;

    }

    public String getSearchText() {
        return searchText;
    }

    public List<String> getMovieTitles() {
        return movieTitles;
    }

    public List<String> getPosterPaths() {
        return posterPaths;
    }

    public List<CommonMovie> getResults() {
        return results;
    }
}
