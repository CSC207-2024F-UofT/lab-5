import com.deepl.api.*;

public class Example {
    private Translator translator;

    public Example() throws Exception {
        final String authKey = "59431b39-31eb-423d-9fcd-b86eb38b23e0:fx";  // Replace with your key
        translator = new Translator(authKey);
        final TextResult result =
                translator.translateText("Hello, world!", null, "fr");
        System.out.println(result.getText()); // "Bonjour, le monde !"
    }

    public static void main(String[] args) {
        try {
            final String authKey = "59431b39-31eb-423d-9fcd-b86eb38b23e0:fx";  // Replace with your key
            final Translator translator2 = new Translator(authKey);
            final TextResult result = translator2.translateText("How are you doing", null, "ja");
            System.out.println(result.getText());
        }
        catch (InterruptedException e) {
        }
        catch (DeepLException e) {}
}}
