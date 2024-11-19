package entity;

import java.util.Date;

public class CommonMovieReview implements MovieReview {
    private final String user;
    private final Date date;
    private final Double starRating;
    private final String content;
    private final String movie_Title;

    public CommonMovieReview(String user, Date date, Double starRating, String content, String movie_Title) {
        this.user = user;
        this.date = date;
        this.starRating = starRating;
        this.content = content;
        this.movie_Title = movie_Title;

    }
    // Overloaded constructor

    public CommonMovieReview(String user, Date date, Double starRating, String movie_Title) {
        this.user = user;
        this.date = date;
        this.starRating = starRating;
        this.content = null;
        this.movie_Title = movie_Title;
    }

    public Date getDate() {
        return this.date;
    }

    public String getUser() {
        return this.user;
    }

    public Double getStarRating() {
        return this.starRating;
    }

    public String getContent() {
        return this.content;
    }

    public String getMovie_Title() {
        return this.movie_Title;
    }
}


