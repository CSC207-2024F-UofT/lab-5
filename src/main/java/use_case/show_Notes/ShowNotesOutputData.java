package use_case.show_Notes;

import java.util.ArrayList;
import java.util.List;

/**
 * The output data for the show discussions usecase.
 */
public class ShowNotesOutputData {

    private List<String> topics = new ArrayList<>();

    public ShowNotesOutputData(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getTopics() {
        return topics;
    }
}

