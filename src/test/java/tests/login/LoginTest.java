package tests.login;

import framework.auth.AuthService;
import framework.config.Environment;
import framework.http.ApiClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    void login_shouldReturnToken() {
        ApiClient api = new ApiClient(Environment.getBaseUrl());
        AuthService authService = new AuthService(api);

        String token = authService.login();

        assertNotNull(token, "Authentication should return a token");
        assertFalse(token.isEmpty(), "Token should not be empty");
    }
}
