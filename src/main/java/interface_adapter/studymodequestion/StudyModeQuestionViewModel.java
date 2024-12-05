package interface_adapter.studymodequestion;

import interface_adapter.ViewModel;

/**
 * View Model for Study Mode Question use case.
 */
public class StudyModeQuestionViewModel extends ViewModel<StudyModeQuestionState> {

    public static final String STUDY_MODE_QUESTION_BUTTON_LABEL = "begin";

    public StudyModeQuestionViewModel() {
        super("study mode question");
        setState(new StudyModeQuestionState());
    }
}
