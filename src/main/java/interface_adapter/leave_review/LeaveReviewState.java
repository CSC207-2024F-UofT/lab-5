package interface_adapter.leave_review;

import interface_adapter.home.LoggedInState;
import interface_adapter.movie.MovieState;

public class LeaveReviewState {
    private final LoggedInState loggedInState;
    private final MovieState movieState;

    public LeaveReviewState(LoggedInState loggedInState, MovieState movieState) {
        this.loggedInState = loggedInState;
        this.movieState = movieState;
    }

    public String getUsername() {
        return loggedInState.getUsername();
    }

    public String getMovieName() {
        return movieState.getTitle();
    }
}
