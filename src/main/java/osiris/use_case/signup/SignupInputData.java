package osiris.use_case.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String email;
    private final String password;
    private final String repeatPassword;
    private final String access_code;

    public SignupInputData(String email, String password, String repeatPassword, String access_code) {
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.access_code = access_code;
    }

    String getEmail() {
        return email;
    }

    String getPassword() {
        return password;
    }

    String getAccessCode() {
        return access_code;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
