package data_access;

import java.util.List;

import entity.CommonMovie;
import entity.CommonMovieFactory;
import use_case.movie.MovieUserDataAccessInterface;

/**
 * The DAO for movie data.
 */
public class DBMovieDataAccessObject implements MovieUserDataAccessInterface {

    private static CommonMovieFactory movieFactory = new CommonMovieFactory();

    public DBMovieDataAccessObject(CommonMovieFactory movieFactory) {
        this.movieFactory = movieFactory;
    }

    @Override
    public boolean existsByName(String title) {
        final List<CommonMovie> result = APIMovieAccess.formatedSearchedMovies(title);
        return !result.isEmpty();
    }

    @Override
    public CommonMovie getMovieByName(String title) {
        return null;
    }
}
