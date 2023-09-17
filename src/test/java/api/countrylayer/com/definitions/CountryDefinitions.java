package api.countrylayer.com.definitions;
import api.countrylayer.com.utils.SerenityProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.HashMap;

import static api.countrylayer.com.utils.ApiUtils.*;
import static api.countrylayer.com.utils.CountriesList.getCountryCode;
import static api.countrylayer.com.utils.RetryTime.retryUntilTimeout;
import static org.assertj.core.api.Assertions.assertThat;

public class CountryDefinitions {

    private String baseUrl;
    private String apiKey;

    @Given("the base URL is configured in serenity.properties")
    public void configureBaseUrl() {
        baseUrl = SerenityProperties
                .getProperty("base.url");
    }

    @And("the API key is configured in serenity.properties")
    public void configureApiKey() {
        apiKey = SerenityProperties
                .getProperty("api.key");
    }

    @When("I request information for country {string}")
    public void requestCountryInformation(String country) {
        String countryCode = getCountryCode(country);
        String apiUrl = baseUrl + "/alpha/" + countryCode + "?access_key=" + apiKey;
        retryUntilTimeout(() -> sendGetRequest(apiUrl));
    }

    @When("I send a POST request to create a new country")
    public void postNewCountry() {
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Test Country");
        requestBody.put("alpha2_code", "TC");
        requestBody.put("alpha3_code", "TCY");

        String accessToken = SerenityProperties
                .getProperty("access.token");
        String apiUrl = baseUrl + "/alpha" + "/create-country" + "?access_key=" + apiKey;
        sendPostRequest(apiUrl, requestBody, accessToken);
    }

    @Then("the response code should be {int}")
    public void verifyResponseCode(int expectedResponseCode) {
        assertThat(responseCode).isEqualTo(expectedResponseCode);
    }

}
