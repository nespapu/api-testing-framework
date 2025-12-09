package framework.http;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiClient {

    private final String baseUrl;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
    }

    public Response post(String endpoint, Object body) {
        return RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(body)
                .when()
                    .post(endpoint)
                .then()
                    .extract()
                    .response();
    }

    public Response post(String endpoint, Object body, String token) {
        return RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .header("Cookie", "token=" + token)
                    .body(body)
                .when()
                    .post(endpoint)
                .then()
                    .extract()
                    .response();
    }

    public Response get(String endpoint) {
        return RestAssured
                .given()
                .when()
                    .get(endpoint)
                .then()
                    .extract()
                    .response();
    }

    public Response put(String endpoint, Object body, String token) {
        return RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .header("Cookie", "token=" + token)
                    .body(body)
                .when()
                    .put(endpoint)
                .then()
                    .extract()
                    .response();
    }

    public Response delete(String endpoint, String token) {
        return RestAssured
                .given()
                    .header("Cookie", "token=" + token)
                .when()
                    .delete(endpoint)
                .then()
                    .extract()
                    .response();
    }
}
