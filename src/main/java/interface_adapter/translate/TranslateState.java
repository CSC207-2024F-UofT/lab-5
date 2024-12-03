package interface_adapter.translate;

/**
 * The State information for managing translation requests.
 */
public class TranslateState {

    private String inputLanguage = "";
    private String outputLanguage = "";
    private String inputText = "";
    private String outputText = "";
    private String translatedText = "";
    private String translationError;

    // Copy constructor
    public TranslateState(TranslateState copy) {
        this.inputLanguage = copy.inputLanguage;
        this.outputLanguage = copy.outputLanguage;
        this.inputText = copy.inputText;
        this.outputText = copy.outputText;
        this.translatedText = copy.translatedText;
        this.translationError = copy.translationError;
    }

    // Default constructor
    public TranslateState() {
    }

    public String getInputLanguage() {
        return inputLanguage;
    }

    public void setInputLanguage(String inputLanguage) {
        this.inputLanguage = inputLanguage;
    }

    public String getOutputLanguage() {
        return outputLanguage;
    }

    public void setOutputLanguage(String outputLanguage) {
        this.outputLanguage = outputLanguage;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslationError() {
        return translationError;
    }

    public void setTranslationError(String translationError) {
        this.translationError = translationError;
    }
}
