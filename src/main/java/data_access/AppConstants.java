package data_access;

public final class AppConstants {

    public static final String API_KEY = "5fcf2eef76af4e6893959ceefae0a087";
    public static final String NUMBER_OF_RESULTS = "5";
    public static final String FONT = "Arial";
    public static final int WELCOME_FONT_SIZE = 18;
    public static final int BUTTON_FONT_SIZE = 16;
    public static final int RESPONSE_CODE_200 = 200;

    // Prevent instantiation
    private AppConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}