package use_case.survey_one;

import java.util.List;

/**
 * Output Data for the Survey1 Use Case.
 */
public class Survey1OutputData {
    private final List<String> selectedGenres;

    public Survey1OutputData(List<String> selectedGenres) {
        this.selectedGenres = selectedGenres;
    }

    /**
     * Returns the list of selected genres.
     *
     * @return a List of selected genres
     */
    public List<String> getSelectedGenres() {
        return this.selectedGenres;
    }
}
