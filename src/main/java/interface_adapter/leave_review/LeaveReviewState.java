package interface_adapter.leave_review;

import interface_adapter.home.LoggedInState;
import interface_adapter.movie.MovieState;

public class LeaveReviewState {
    private String username;
    private String moviename;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getMovieName() {
        return this.moviename;
    }
}
