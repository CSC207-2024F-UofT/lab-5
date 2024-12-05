package interface_adapter.search;

/**
 * The state for the Search View Model.
 */
public class SearchState {
    private String query;
    private boolean isLoading;
    private String errorMessage;

    public SearchState(String query, boolean isLoading, String errorMessage) {
        this.query = query;
        this.isLoading = isLoading;
        this.errorMessage = errorMessage;
    }

    public SearchState() {
        this("", false, "");
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

