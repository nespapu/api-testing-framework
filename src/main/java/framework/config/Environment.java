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
        return getRequiredProperty("baseUrl", "API_BASE_URL");
    }

    public static String getUsername() {
        return getRequiredProperty("username", "API_USERNAME");
    }

    public static String getPassword() {
        return getRequiredProperty("password", "API_PASSWORD");
    }

    private static String getRequiredProperty(String key, String envKey) {
        // 1. Try environment variable
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }

        // 2. Fallback to config.properties
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required configuration for: " + key
                    + " (env var: " + envKey + ")");
        }
        return value;
    }
}
