package api.countrylayer.com.utils;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import java.util.Map;
public class ApiUtils {

    public static int responseCode;

    public static void sendGetRequest(String apiUrl) {
        SerenityRest.given()
                .when()
                .get(apiUrl);
        responseCode = SerenityRest.then().extract().statusCode();
    }

    public static void sendPostRequest(String apiUrl, Map<String, String> requestBody, String accessToken) {
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody)
                .post(apiUrl);
        responseCode = SerenityRest.then().extract().statusCode();
    }
}
