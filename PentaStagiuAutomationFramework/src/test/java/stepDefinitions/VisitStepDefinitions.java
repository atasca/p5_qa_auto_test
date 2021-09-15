package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Visit;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;
import stepImplementation.VisitStepImpl;

import static org.junit.Assert.assertTrue;

public class VisitStepDefinitions {

    String responseString;

    Visit visit = new Visit();

    Visit createdVisit;

    Response response;

    @Steps
    VisitStepImpl visitImpl;


    @Given("The user get the list with all visits")
    public void getVisits () {

        responseString = RestAssured.
                given().
                when().get("http://localhost:9966/petclinic/api/visits").
                then().assertThat().statusCode(201)
                .extract().body().asString();
    }


    @Then("A list of visits is returned")
    public void aListOfVisitsIsReturned () {

        assertTrue(StringUtils.isNotEmpty(responseString));
    }


    @When("The user creates visit with description {}")
    public void createNewSpecificVisit(String description) {
        visitImpl.createNewVisit(description);
        
    }

    @Then("The {} visit is in the response")
    public void cerifyCreatedVisitInTheList(String description) {
        visitImpl.verifyCreatedOwner(description);
    }

    @And("The {} visit is created")
    public void theDescritpionVisitIsCreated(String description) {
        responseString = RestAssured.
                given()
                .pathParam("description", description)
                .when().get("http://localhost:9966/petclinic/api/visits/*/description/{description}")
                .then().assertThat().statusCode(200)
                .extract().body().asString();
    }
}
