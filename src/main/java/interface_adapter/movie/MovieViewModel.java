package interface_adapter.movie;

import interface_adapter.ViewModel;


/**
 * The View Model for the Movie View.
 */
public class MovieViewModel extends ViewModel<MovieState> {

    public static final String TITLE_LABEL = "Movie Information";

    public static final String MOVIE_LABEL = "Movie";
    public static final String OVERVIEW_LABEL = "Overview";
    public static final String GENRE_LABEL = "Genre(s)";
    public static final String OUR_RATINGS_LABEL = "Our Ratings";
    public static final String VOTER_AVERAGE_LABEL = "Voter Average";
    public static final String BACK_BUTTON_LABEL = "back_button";
    public static final String HOME_LABEL = "Home";
    public static final String PWL_LABEL = "I watched this";
    public static final String ADD_TO_LIST_LABEL = "Add to List";
    public static final String USER_REVIEWS_LABEL = "User Reviews";
    public static final String LEAVE_REVIEW_LABEL = "Leave a Review";

    public MovieViewModel() {
        super("movie");
        setState(new MovieState());
    }
}
