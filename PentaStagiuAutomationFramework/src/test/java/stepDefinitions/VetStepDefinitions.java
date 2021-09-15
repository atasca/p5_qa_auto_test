package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;
import stepImplementation.VetStepImpl;


import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class VetStepDefinitions {
    String responseString;

    @Steps
    VetStepImpl vetStep;

    @Given("The user get the list with all vets")
    public void getVet() {
        responseString = RestAssured.
                given().
                when().get("http://localhost:9966/petclinic/api/vets").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }

    @Then("A list of vets is returned")
    public void aListOfVetsIsReturned() {
        assertTrue(StringUtils.isNotEmpty(responseString));
    }

    @When("The user get the vet with id {}")
    public void getVetId(String id) {
        responseString = RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/vets/{id}").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }

    @Then("A vet is returned")
    public void aVetIsReturned() {
        assertTrue(StringUtils.isNotEmpty(responseString));
    }

    @When("The user updates vet that have id {} with new firstName {}")
    public void updateVetFirstName(String id, String firstName, List<Map<String, String>> table) {
        vetStep.updateVetFirstName(id, firstName, table);
    }

    @Then("The vet firstName with id {} is updated with {}")
    public void vetFirstNameUpdate(String id, String firstName) {
        vetStep.vetFirstNameUpdate(id, firstName);

    }

    @When("The user get delete vet with id {}")
    public void DeleteVet(String id) {
        vetStep.DeleteVet(id);
    }

    @Then("The vet with id {} is deleted")
    public void aVetIsDeleted(String id) {
        vetStep.aVetIsDeleted(id);
    }
}


