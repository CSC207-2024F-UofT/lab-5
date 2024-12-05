package use_case.search;

/**
 * Output Data for the `Search` Use Case.
 */
public class SearchOutputData {
    // Make it a list of String for the book title

    private String title;
    private String author;
    private String key;
    private String coverUrl;
    private String query = "";
    private boolean useCaseFailed;

    public SearchOutputData(String title, String author, String key, String coverUrl, boolean useCaseFailed) {
        this.title = title;
        this.author = author;
        this.key = key;
        this.coverUrl = coverUrl;
        this.query = "";
        this.useCaseFailed = useCaseFailed;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getKey() {
        return key;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public void setUseCaseFailed(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

}
