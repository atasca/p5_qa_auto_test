package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Owner;
import models.Pet;
import models.Type;
import models.Visit;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;
import stepImplementation.OwnerStepImpl;
import stepImplementation.PetStepImpl;
import stepImplementation.PetTypeStepImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class PetTypeStepDefinitions {

    String responseString;

    Type type = new Type();

    Type createdPetType;

    Response response;

    @Steps
    PetTypeStepImpl petTypeImpl;

    @Given("The user get the list with all pettypes")
    public void getPets () {

        responseString = RestAssured.
                given().
                when().get("http://localhost:9966/petclinic/api/pettypes").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }


    @Then("A list of pettypes is returned")
    public void aListOfPetTypesIsReturned () {

        assertTrue(StringUtils.isNotEmpty(responseString));
    }


    @Then("The {} pettype is in the response")
    public void verifyCreatedPetTypeInTheList(String name) {

        petTypeImpl.verifyCreatedPetType(name);
    }

    @When("The user creates pet with name {}")
    public void createSpecificPetType(String name) {
        petTypeImpl.createNewPetType(name);
    }


    @And("The {} pet is created")
    public void verifyCreatedPetType(String name) {

        responseString = RestAssured.
                given()
                .pathParam("name", name)
                .when().get("http://localhost:9966/petclinic/api/pettypes/*/name/{name}")
                .then().assertThat().statusCode(200)
                .extract().body().asString();
    }
}
