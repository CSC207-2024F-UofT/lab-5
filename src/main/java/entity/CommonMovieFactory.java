package entity;

import java.util.List;

/**
 * Factory for creating CommonMovie objects.
 */
public class CommonMovieFactory implements MovieFactory {

    public static Movie create(String title) {
        return new CommonMovie(title);
    }
}
