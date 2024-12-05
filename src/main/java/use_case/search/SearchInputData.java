package use_case.search;

/**
 * Input data for the search usecase.
 */
public class SearchInputData {
    private final String query;

    public SearchInputData(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
