package entity;

import com.deepl.api.*;

/**
 * Translator.
 */
public class Translate {

    private final String inputLanguage;
    private final String outputLanguage;
    private final String inputText;
    private final String outputText;

    public Translate(String inputLanguage, String outputLanguage, String inputText) {
        this.inputLanguage = inputLanguage;
        this.outputLanguage = outputLanguage;
        this.inputText = inputText;
        this.outputText = translateText(inputLanguage, outputLanguage, inputText);
    }

    public String getInputLanguage() {
        return inputLanguage;
    }

    public String getOutputLanguage() {
        return outputLanguage;
    }

    public String getInputText() {
        return inputText;
    }

    /**
     * Translate the given text.
     * @param inputLang input language
     * @param outputLang output language
     * @param input input text
     * @return The result of the translation.
     */
    private String translateText(String inputLang, String outputLang, String input) {
        String output = null;
        try {
            final String authKey = "563c8dbf-539b-48c2-bc82-fae6a6622677:fx";
            final Translator translator = new Translator(authKey);
            final TextResult result = translator.translateText(input, inputLang, outputLang);
            output = result.getText();
        }
        catch (DeepLException exception) {
            System.out.println(exception.getMessage());
        }
        catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }
        return output;
    }

    public String getOutputText() {
        return outputText;
    }

    public static void main(String[] args) {
        Translate translate = new Translate("ZH", "JA", "你好");
        System.out.println(translate.getOutputText());
    }
}