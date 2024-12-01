package interface_adapter.translate;

import interface_adapter.ViewModel;

/**
 * The ViewModel for Translator.
 */
public class TranslateViewModel extends ViewModel<TranslateState> {

    public static final String TITLE_LABEL = "Translator";
    public static final String LOGOUT_LABEL = "Log out";

    public static final String INPUT_LANGUAGE_LABEL = "Choose your input language";
    public static final String OUTPUT_LANGUAGE_LABEL = "Choose your output language";

    public static final String INPUT_TEXT_LABEL = "Input your text here";
    public static final String OUTPUT_TEXT_LABEL = "Your translation will be here";

    public static final String TRANSLATE_F_LABEL = "Translate";
    public static final String OTHER_TRANSLATION_F_LABEL = "Others";

    public static final String HISTORY_LABEL = "History";

    public static final String BOOKMARK_F_LABEL = "Bookmark current translation";
    public static final String BOOKMARKS_LABEL = "Bookmarks";

    public static final String FEEDBACK_F_LABEL = "Submit a feedback";

    public TranslateViewModel() {
        super("translate");
        setState(new TranslateState());
    }

    /**
     * Updates the input text in the state and fires a property change event.
     *
     * @param inputText the text to be translated
     */
    public void updateInputText(String inputText) {
        getState().setInputText(inputText);
        firePropertyChanged("inputText");
    }

    /**
     * Updates the translated text in the state and fires a property change event.
     * @param translatedText translated text
     */
    public void updateTranslatedText(String translatedText) {
        getState().setTranslatedText(translatedText);
        firePropertyChanged("translatedText");
    }

    /**
     * Updates the error message in the state and fires a property change event.
     * @param errorMessage error message
     */
    public void updateError(String errorMessage) {
        getState().setTranslateError(errorMessage);
        firePropertyChanged("translateError");
    }

    /**
     * Fires a property change for the specified property.
     * @param propertyName fail or success
     */
    public void firePropertyChanged(String propertyName) {
        // Notify observers or UI components that the property has changed.
        // Implementation depends on the ViewModel base class or observer pattern.
        System.out.println("Property changed: " + propertyName);
    }
}
