package api.countrylayer.com.definitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class CountryDefinitions {

    private static final String URI = "http://api.countrylayer.com/v2";
    private static final String API_KEY = "?access_key=80829a29de1506ad64d4d715b5da0183";
    public Response response;

    @Given("the base URL and API Key are configured")
    public void sendRequest() {
        response = SerenityRest.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .when().get(URI + "/all" + API_KEY);
    }

    @When("make a GET request to {string}")
    public void getCountry(String country){
        response = SerenityRest.given()
                .when().get(URI+"/alpha/" + country + API_KEY);
        response.prettyPrint();
    }

    @Then("{string} get the status code {int}")
    public void getStatusCodeOfCountry(String country, int code){
        response = SerenityRest.given()
                .when().get(URI+"/alpha/" + country + API_KEY);
        response.then().statusCode(code);
        //When it's a registered country, the response is 200
        //When it's a non-existent country, the response is 404
        //But the API is over saturated of requests
        response.prettyPrint();
    }

    @When("make a POST request inserting {string}, {string} and {string}")
    public void postNewCountry(String name, String alpha2_code, String alpha3_code){
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("alpha2_code", alpha2_code);
        requestBody.put("alpha3_code", alpha3_code);

        response = SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_KEY)
                .body(requestBody)
                .post(URI + "/create-country");
    }

    @Then("the POST request get the status code {int}")
    public void getPostStatusCode(int code){
        response = SerenityRest.given()
                .when().get(URI+"/all/"+ API_KEY);
        response.then().statusCode(code);
        //When it's registered, the status code would be 201
        response.prettyPrint();
    }

}
