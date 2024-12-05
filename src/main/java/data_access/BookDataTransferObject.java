package data_access;

/**
 * A storage class after book parsing.
 */
public class BookDataTransferObject {
    private String title;
    private String author;
    private String key;
    private String coverUrl;

    public BookDataTransferObject(String title, String author, String key, String coverUrl) {
        this.title = title;
        this.author = author;
        this.key = key;
        this.coverUrl = coverUrl;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("title " + title);
        sb.append("author " + author);
        sb.append("key " + key);
        sb.append("coverUrl " + coverUrl);
        return sb.toString();
    }
}
