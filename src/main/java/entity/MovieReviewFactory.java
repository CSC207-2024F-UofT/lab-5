package entity;

import java.util.Date;

/**
 * The interface for our all factories that create movie object.
 */
public interface MovieReviewFactory {
    /**
     * Create movie review method for movie reviews which include a written component.
     * @param user user object.
     * @param date date object.
     * @param starRating double.
     * @param writtenReview the written review content.
     * @param movie_Title the title of this movie.
     * @return MovieReview object.
     */
    MovieReview create(String user, Date date, Double starRating, String writtenReview, String movie_Title);
    /**
     * Create movie review method for movie reviews which there is no written component.
     * @param user user object.
     * @param date date object.
     * @param starRating double.
     * @param movie_Title the title of this movie.
     * @return MovieReview object.
     */

    MovieReview create(String user, Date date, Double starRating, String movie_Title);
}
