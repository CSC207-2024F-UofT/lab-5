package interface_adapter.survey_second_page;

import interface_adapter.ViewModel;

/**
 * View model.
 */
public class SurveySecondPageViewModel extends ViewModel<SurveySecondPageState> {

    public static final String ENTER_LABEL = "Enter";
    public static final String SUBMIT_LABEL = "Submit";
    public static final String SKIP_LABEL = "Skip";

    public SurveySecondPageViewModel() {
        super("survey page: 2/2");
        setState(new SurveySecondPageState());
    }
}
