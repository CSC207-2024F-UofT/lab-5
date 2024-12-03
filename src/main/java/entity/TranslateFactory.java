package entity;

public class TranslateFactory {

    public Translate create(String inputLanguage, String outputLanguage, String inputText) {
        return new Translate(inputLanguage, outputLanguage, inputText);
    }
}
