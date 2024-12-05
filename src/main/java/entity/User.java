package entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A user class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private final String name;
    private final String password;
    private List<Book> readBooks;
    private List<Book> booksToRead;
    private List<Book> recommendedBooks;
    private List<BookClub> joinedClubs;
    private List<Review> reviews;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Book> getReadBooks() {
        return readBooks;
    }

    /**
     * Adds a new book to readBooks list.
     * @param newBook book added to list
     */
    public void addReadBooks(Book newBook) {
        this.readBooks.add(newBook);
    }

    public List<Book> getBooksToRead() {
        return booksToRead;
    }

    /**
     * Adds a new book to booksToRead list.
     * @param newBook book added to the list
     */
    public void addBookToRead(Book newBook) {
        this.booksToRead.add(newBook);
    }

    public List<Book> getRecommendedBooks() {
        return recommendedBooks;
    }

    /**
     * Adds a new book to recommendedBooks list.
     * @param newBook book added to the list
     */
    public void addRecommendedBook(Book newBook) {
        this.recommendedBooks.add(newBook);
    }

    public List<BookClub> getJoinedClubs() {
        return joinedClubs;
    }

    public void setJoinedClubs(List<BookClub> joinedClubs) {
        this.joinedClubs = joinedClubs;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return name;
    }
}
