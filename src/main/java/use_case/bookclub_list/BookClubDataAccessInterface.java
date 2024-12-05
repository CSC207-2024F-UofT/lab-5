package use_case.bookclub_list;

import java.util.List;

import entity.BookClub;

/**
 * Interface for data access operations related to book clubs.
 * Defines methods for interacting with book club data.
 */
public interface BookClubDataAccessInterface {

    /**
     * Retrieves all the book clubs.
     *
     * @return a list of all book clubs
     */
    List<BookClub> getAllClubs();

}
