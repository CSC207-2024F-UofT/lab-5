package entity;

/**
 * Factory for creating CommonMovie objects.
 */
public class CommonMovieFactory implements MovieFactory {
    /**
     * Create a movie object.
     * @param title the title of this movie.
     * @return Movie.
     */
    public static Movie create(String title) {
        return new CommonMovie(title);
    }
}
