package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public final class Environment {

    private static final String DEFAULT_ENV = "local";
    private static final String ENV_VAR_ENV = "API_ENV";

    private static final Properties PROPERTIES = new Properties();
    private static final String ACTIVE_ENV = resolveActiveEnv();

    static {
        loadPropertiesFile("config.properties", false);

        String envSpecificFile = "config-" + ACTIVE_ENV + ".properties";
        loadPropertiesFile(envSpecificFile, true);
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

    public static String getActiveEnvironment() {
        return ACTIVE_ENV;
    }

    private static String resolveActiveEnv() {
        String envFromVar = System.getenv(ENV_VAR_ENV);
        if (envFromVar != null && !envFromVar.isBlank()) {
            return envFromVar.toLowerCase(Locale.ROOT);
        }
        return DEFAULT_ENV;
    }

    private static void loadPropertiesFile(String fileName, boolean failIfMissing) {
        try (InputStream input = Environment.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (input == null) {
                if (failIfMissing) {
                    throw new IllegalStateException("Configuration file not found: " + fileName);
                }
                return;
            }

            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load configuration file: " + fileName, e);
        }
    }

    private static String getRequiredProperty(String key, String envKey) {
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }

        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Missing required configuration for key: " + key +
                    " (env var: " + envKey + ", env: " + ACTIVE_ENV + ")"
            );
        }

        return value;
    }
}
