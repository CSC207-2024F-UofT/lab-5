package entity;

import com.deepl.api.*;

/**
 * Translator.
 */
public class Translate {

    private final String inputLanguage;
    private final String outputLanguage;
    private final String inputText;

    public Translate(String inputLanguage, String outputLanguage, String inputText) {
        this.inputLanguage = inputLanguage;
        this.outputLanguage = outputLanguage;
        this.inputText = inputText;
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
     * @return The result of the translation.
     */
    public static String translateText(String inputText, String inputLanguage, String outputLanguage) {
        String output = null;
        try {
            final String authKey = "563c8dbf-539b-48c2-bc82-fae6a6622677:fx";
            final Translator translator = new Translator(authKey);
            final TextResult result = translator.translateText(inputText, inputLanguage, outputLanguage);
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
}
