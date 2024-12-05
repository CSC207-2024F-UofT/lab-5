package use_case.email_validation;

import java.io.IOException;
import java.util.Base64;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * VerifaliaEmailValidator is a utility class that provides email validation
 * functionality using the Verifalia API. This class handles the process of
 * sending email validation requests and interpreting the results.
 */
public class VerifaliaEmailValidator {

    // Replace with your Verifalia username (Account SID) and API key
    private static final String USERNAME = "terryzx.huang@mail.utoronto.ca";
    private static final String API_KEY = "ukuYNkz#TGNV98y";

    private static final String API_URL = "https://api.verifalia.com/v2.6/email-validations";

    /**
     * Validates an email address by sending it to the Verifalia API.
     * This method constructs a POST request with the given email address in JSON format
     * and sends it to the Verifalia email validation endpoint. It returns true if the email
     * is valid, and false otherwise.
     * @param email The email address to be validated.
     * @return true if the email is valid; false otherwise.
     * @throws IOException If there is a network or communication error during the request.
     */
    public static boolean validateEmail(String email) throws IOException {
        final OkHttpClient client = new OkHttpClient();

        // Encode credentials for Basic Auth
        final String credentials = USERNAME + ":" + API_KEY;
        final String basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        // JSON body with the email to validate
        final String jsonBody = "{ \"entries\": [ { \"inputData\": \"" + email + "\" } ] }";

        // Build the request
        final Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", basicAuth)
                .header("Content-Type", "application/json")
                .post(RequestBody.create(jsonBody, MediaType.parse("application/json")))
                .build();

        boolean isValid = false;
        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                final String responseBody = response.body().string();
                // Check if the API response indicates the email is valid
                isValid = responseBody.contains("\"status\":\"Success\"");
            }
        }

        return isValid;
    }

}
