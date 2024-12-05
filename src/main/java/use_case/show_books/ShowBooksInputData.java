package use_case.show_books;

/**
 * Input data for my clubs usecase.
 */
public class ShowBooksInputData {

    private String clubName;

    public ShowBooksInputData(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }
}
