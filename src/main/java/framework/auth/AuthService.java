package framework.auth;

import framework.config.Environment;
import framework.http.ApiClient;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private final ApiClient apiClient;

    public AuthService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public String login() {
        Map<String, String> body = new HashMap<>();
        body.put("username", Environment.getUsername());
        body.put("password", Environment.getPassword());

        Response res = apiClient.post("/auth", body);

        return res.jsonPath().getString("token");
    }

}
