package interface_adapter.search;

/**
 * A storage device for book details used by the Presenter.
 */
public class BookViewModel {
    private String title;
    private String author;
    private String key;
    private String coverUrl;

    public BookViewModel(String title, String author, String key, String coverUrl) {
        this.title = title;
        this.author = author;
        this.key = key;
        this.coverUrl = coverUrl;
    }

    public BookViewModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
