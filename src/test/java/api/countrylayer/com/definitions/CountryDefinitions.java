package api.countrylayer.com.definitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class CountryDefinitions {

    private String baseUrl;
    private String apiKey;
    private int responseCode;
    private Response response;

    @Given("the base URL is configured in serenity.properties")
    public void configureBaseUrl() {
        baseUrl = getProperty("base.url");
    }

    @And("the API key is configured in serenity.properties")
    public void configureApiKey() {
        apiKey = getProperty("api.key");
    }

    @When("I request information for country {string}")
    public void requestCountryInformation(String country){
        String countryCode = getCountryCode(country);
        String apiUrl = baseUrl + "/alpha/" + countryCode + "?access_key=" + apiKey;

        SerenityRest.given()
                .when()
                .get(apiUrl);

        responseCode = SerenityRest.then().extract().statusCode();
        System.out.println(apiUrl);
        System.out.println(responseCode);
    }

    @Then("the response code should be {int}")
    public void verifyResponseCode(int expectedResponseCode) {
        // Use 'responseCode' to assert the actual response code
        assertThat(responseCode).isEqualTo(expectedResponseCode);
    }

    @When("I send a POST request to create a new country")
    public void postNewCountry(){
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Test Country");
        requestBody.put("alpha2_code", "TC");
        requestBody.put("alpha3_code", "TCY");

        String accessToken = getProperty("access.token");
        response = SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody).log().all()
                .post(baseUrl + "/alpha" + "/create-country" + "?access_key=" + apiKey);
    }

    private String getCountryCode(String country) {
        switch (country) {
            case "US":
                return "US";
            case "DE":
                return "DE";
            case "GB":
                return "GB";
            default:
                return country;
        }
    }

    private String getProperty(String propertyName) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("serenity.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertyName);
    }

}
