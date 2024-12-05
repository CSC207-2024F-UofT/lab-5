package entity;

import java.util.Date;

/**
 * The interface defining methods that all MovieReview objects must implement.
 */
public interface MovieReview {

    /**
     * A method to get the title of this movie review.
     * @return the title of the movie assoicated with this review.
     */
    String getMovieTitle();

    /**
     * A method to get the date associated with this movie review.
     * @return the date this movie review was created.
     */
    Date getDate();

    /**
     * Returns the user who made this review.
     * @return the userID.
     */
    String getUserID();

    /**
     * The method to get the content of this movie review, may return nothing if not written review exists.
     * @return A string representation of this written review.
     */
    String getContent();

    /**
     * The star rating of this review.
     * @return a double representing the star rating.
     */
    Double getStarRating();
}
