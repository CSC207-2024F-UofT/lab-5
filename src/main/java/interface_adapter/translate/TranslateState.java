package interface_adapter.translate;

import java.util.ArrayList;
import java.util.List;

import entity.Translate;

public class TranslateState {
    private String inputText;
    private String translatedText;
    private String sourceLanguage;
    private String targetLanguage;
    private final List<String> translationHistory;

    public TranslateState() {
        this.translationHistory = new ArrayList<>();
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getInputText() {
        return inputText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void addTranslationToHistory(String translation) {
        translationHistory.add(translation);
    }

    public List<String> getTranslationHistory() {
        return new ArrayList<>(translationHistory);
    }

    public boolean performTranslation() {
        if (inputText == null || sourceLanguage == null || targetLanguage == null) {
            System.out.println("Translation parameters are missing.");
            return false;
        }

        this.translatedText = Translate.translateText(inputText, sourceLanguage, targetLanguage);
        addTranslationToHistory(translatedText);
        return true;
    }
}
