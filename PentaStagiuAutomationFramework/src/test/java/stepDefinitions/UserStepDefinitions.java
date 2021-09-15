package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import stepImplementation.UserStepImpl;

public class UserStepDefinitions {

    String responseString;

    Response response;

    @Steps
    UserStepImpl userImpl;

    @When("Create user with username {}")
    public void createNewSpecificUser (String username) {

        userImpl.createNewUser(username);
    }


    @Then("The {} user is in the response")
    public void verifyCreatedUserInTheList (String username) {

        userImpl.verifyCreatedUser(username);
    }

    @Then("The {} user is created")
    public void verifyCreatedUser (String username) {

        responseString = RestAssured.
                given()
                .pathParam("username", username)
                .when().get("http://localhost:9966/petclinic/api/users/*/username/{username}")
                .then().assertThat().statusCode(200)
                .extract().body().asString();
    }
}
