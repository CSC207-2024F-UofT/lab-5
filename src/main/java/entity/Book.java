package entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class representing a book.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    private String title;
    private String author;
    private String genre;
    private double rating;
    private List<Review> reviews;
    private String coverUrl;
    private String iSbn;

    public Book(String title, String author, String genre, double rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
        this.reviews = new ArrayList<>();
        this.coverUrl = "";
        this.iSbn = "";
    }

    // Default constructor (required for deserialization)
    public Book() {
    }

    public Book(String title, String author, String Isbn, String coverUrl) {
        this.title = title;
        this.author = author;
        this.iSbn = Isbn;
        this.coverUrl = coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Adds new review to reviews list.
     * @param review the new review
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    private void setTitle(String newTitle) {
        this.title = newTitle;
    }

    private void setAuthor(String newAuthor) {
        this.author = newAuthor;
    }

    private void setGenre(String newGenre) {
        this.genre = newGenre;
    }

    private void setRating(double newRating) {
        this.rating = newRating;
    }

}
