package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;

import stepImplementation.VetStepImpl;

import static org.junit.Assert.assertTrue;

public class VetStepDefinitions {

    String responseString;

    Response response;

    @Steps
    VetStepImpl vetImpl;


    @Given("The user get the list with all vets")
    public void getVets () {

        responseString = RestAssured.
                given().
                when().get("http://localhost:9966/petclinic/api/vets").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }


    @Then("A list of vets is returned")
    public void aListOfVetsIsReturned () {

        assertTrue(StringUtils.isNotEmpty(responseString));
    }
}
