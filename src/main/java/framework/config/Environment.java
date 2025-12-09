package framework.config;

public final class Environment {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password123";

    private Environment() {
        // Prevent instantiation
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
