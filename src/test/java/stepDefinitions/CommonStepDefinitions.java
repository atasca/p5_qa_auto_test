package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import stepImplementation.CommonStepImpl;

public class CommonStepDefinitions {
    CommonStepImpl commonStep= new CommonStepImpl();
    @Given("The PetClinic application is up and running")
    public void verifyTheApplication() {
        RestAssured.
                given()
                .when()
                .get("http://localhost:9966/petclinic/swagger-ui.html#/")
                .then()
                .assertThat().statusCode(200);
    }
    @Then("the status code is {int}")
    public void checkStatus(int statusCode){
        commonStep.checkStatus(statusCode);
    }



}
