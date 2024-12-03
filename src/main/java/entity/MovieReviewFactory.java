package entity;

import javax.xml.crypto.Data;
import java.util.Date;

public interface MovieReviewFactory {

    MovieReview create(String user, Date date, Double starRating, String writtenReview, String movie_Title);

    // Overloaded constructor in case there is no written review
    MovieReview create(String user, Date date, Double starRating, String movie_Title);
}
