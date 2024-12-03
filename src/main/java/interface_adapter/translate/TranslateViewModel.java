package interface_adapter.translate;

import interface_adapter.ViewModel;

/**
 * The ViewModel for Translator.
 */

public class TranslateViewModel extends ViewModel<TranslateState> {

    public static final String TITLE_LABEL = "Translator";
    public static final String LOGOUT_LABEL = "Log out";

    public static final String INPUT_LANGUAGE_LABEL = "Choose your inout language";
    public static final String OUTPUT_LANGUAGE_LABEL = "Choose your output language";

    public static final String INPUT_TEXT_LABEL = "Input your text here";
    public static final String OUTPUT_TEXT_LABEL = "Your translation will be here";

    public static final String TRANSLATE_F_LABEL = "Translate";
    public static final String OTHER_TRANSLATION_F_LABEL = "Others";

    public static final String HISTORY_LABEL = "History";

    public static final String BOOKMARK_F_LABEL = "Bookmark current translation";
    public static final String BOOKMARKS_LABEL = "Bookmarks";

    public static final String FEEDBACK_F_LABEL = "Submit feedback";

    public TranslateViewModel() {
        super("translate");
        setState(new TranslateState());
    }

}
