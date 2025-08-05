package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;
import io.cucumber.java.Before;

public class Hooks {


    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

//write a code that will give the place_id
        StepDefinition m = new StepDefinition();
        if (StepDefinition.place_Id == null) {
            m.add_place_payload_with("Shetty", "French", "Asia");
            m.user_calls_with_http_request("AddPlaceAPI", "POST");
            m.verify_place_Id_created_maps_to_using("Shetty", "getPlaceAPI");

        }
        System.out.println("Place ID: " + StepDefinition.place_Id);
    }

}
