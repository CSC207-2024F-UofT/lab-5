package use_case.survey_one;

import java.util.ArrayList;
import java.util.List;

/**
 * The Input Data for the Survey1 Use Case.
 */
public class Survey1InputData {

    private final ArrayList<String> selectedGenres;

    public Survey1InputData(List<String> selectedGenres) {
        this.selectedGenres = (ArrayList<String>) selectedGenres;
    }

    public List<String> getSelectedGenres() {
        return selectedGenres;
    }
}
