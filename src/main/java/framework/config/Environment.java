package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Environment {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = Environment.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new IllegalStateException("config.properties file not found in classpath");
            }

            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config.properties", e);
        }
    }

    private Environment() {
        // Prevent instantiation
    }

    public static String getBaseUrl() {
        return getRequiredProperty("baseUrl");
    }

    public static String getUsername() {
        return getRequiredProperty("username");
    }

    public static String getPassword() {
        return getRequiredProperty("password");
    }

    private static String getRequiredProperty(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required property: " + key);
        }
        return value;
    }
}
