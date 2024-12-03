package entity;

import java.util.Date;

public interface MovieReview {

    // Method to get the movie title
    String getMovieTitle();

    // Method to get the date of the review
    Date getDate();

    // Method to get the user who made the review
    String getUserID();

    // Method to get the content of the review, may return no comments if none exist.
    String getContent();

    // Method to get star rating if one exists
    Double getStarRating();
}
