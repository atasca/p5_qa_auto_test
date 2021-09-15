package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import org.apache.commons.lang3.StringUtils;
import stepImplementation.VisitStepImpl;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class VisitStepDefinitions {
    String responseString;
    VisitStepImpl visitStep;

    @Given("The user get the list with all visits")
    public void getvisits() {
        responseString = RestAssured.
                given().
                when().get("http://localhost:9966/petclinic/api/visits").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }

    @Then("A list of visits is returned")
    public void aListOfVisitsIsReturned() {
        assertTrue(StringUtils.isNotEmpty(responseString));
    }

    @When("The user creates a new visit")
    public void theUserCreatesANewVisit(List<Map<String, String>> table) {
        visitStep.theUserCreatesANewVisit(table);
    }

    @Then("The {} visit is created")
    public void verifyCreatedvisit(String description) {
        visitStep.verifyCreatedvisit(description);
    }

    @When("The user get the visit with id {}")
    public void getVisitId(String id) {
        responseString = RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/visits/{id}").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }

    @Then("A visit is returned")
    public void aVisitIsReturned() {
        assertTrue(StringUtils.isNotEmpty(responseString));
    }

    @When("The user updates visit that have id {} with new description {}")
    public void updateVisitDescription(Integer id, String description, List<Map<String, String>> table) {
        visitStep.updateVisitDescription(id, description, table);
    }

    @Then("The visit description with id {} is updated with {}")
    public void visitDescriptionUpdate(Integer id, String description) {
        visitStep.visitDescriptionUpdate(id, description);
    }

    @When("The user get delete visit with id {}")
    public void DeleteVisit(Integer id) {
        visitStep.DeleteVisit(id);
    }

    @Then("The visit with id {} is deleted")
    public void aVisitIsDeleted(Integer id) {
        visitStep.aVisitIsDeleted(id);
    }
}
