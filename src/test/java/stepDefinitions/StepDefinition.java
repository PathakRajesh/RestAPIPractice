
package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class StepDefinition extends Utils {

    RequestSpecification res;
    ResponseSpecification resspect;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_Id;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resourse, String method) {
        APIResources resourceAPI = APIResources.valueOf(resourse);
        System.out.println("Calling API: " + resourceAPI.getResource());

        resspect = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        if (method.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getResource());
        } else if (method.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResource());
        } else {
            throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        // Log response for debugging
        System.out.println("API Response: " + response.asString());
    }

    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void status_in_response_body_is(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response, keyValue), expectedValue);
    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {

        // requestSpec
        place_Id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_Id);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);


    }
    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {

        res = given().spec(requestSpecification())
                .body(data.deletePlacePayload(place_Id));
    }
}
