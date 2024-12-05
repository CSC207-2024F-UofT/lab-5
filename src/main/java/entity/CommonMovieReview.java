package entity;

import java.util.Date;

/**
 * A common movie review object.
 */
public class CommonMovieReview implements MovieReview {
    private final String userID;
    private final Date date;
    private final double starRating;
    private final String content;
    private final String movieTitle;

    public CommonMovieReview(String user, Date date, Double starRating, String content, String movieTitle) {
        this.userID = user;
        this.date = date;
        this.starRating = starRating;
        this.content = content;
        this.movieTitle = movieTitle;

    }
    // Overloaded constructor

    public CommonMovieReview(String user, Date date, Double starRating, String movieTitle) {
        this.userID = user;
        this.date = date;
        this.starRating = starRating;
        this.content = null;
        this.movieTitle = movieTitle;
    }

    public Date getDate() {
        return this.date;
    }

    public String getUserID() {
        return this.userID;
    }

    public Double getStarRating() {
        return this.starRating;
    }

    public String getContent() {
        return this.content;
    }

    public String getMovieTitle() {
        return this.movieTitle;
    }
}
