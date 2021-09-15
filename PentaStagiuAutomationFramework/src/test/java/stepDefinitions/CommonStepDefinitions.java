package stepDefinitions;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;

public class CommonStepDefinitions {
    @Given("The PetClinic application is up and running")
    public void verifyTheApplication() {
        RestAssured.
                given()
                .when()
                .get("http://localhost:9966/petclinic/swagger-ui.html#/owner-rest-controller")
                .then()
                .assertThat().statusCode(200);
    }
}
