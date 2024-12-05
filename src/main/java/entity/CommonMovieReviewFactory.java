package entity;

import java.util.Date;

/**
 * A common movie review factory that implements movie review factory and creates common movie review objects.
 */
public class CommonMovieReviewFactory implements MovieReviewFactory {
    /**
     * The create constructor for a movie review that includes a written review.
     * @param user the user id.
     * @param date the date this review was made.
     * @param starRating the star rating given.
     * @param writtenReview the written component of this review.
     * @param movieTitle the title of the movie.
     * @return movie review.
     */
    public MovieReview create(String user, Date date, Double starRating, String writtenReview, String movieTitle) {
        return new CommonMovieReview(user, date, starRating, writtenReview, movieTitle);
    }
    /**
     * The create constructor for a movie review that does not include a written review.
     * @param user the user id.
     * @param date the date this review was made.
     * @param starRating the star rating given.
     * @param movieTitle the title of the movie.
     * @return movie review.
     */

    public MovieReview create(String user, Date date, Double starRating, String movieTitle) {
        return new CommonMovieReview(user, date, starRating, movieTitle);
    }

}
