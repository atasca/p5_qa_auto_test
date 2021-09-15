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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class PetStepDefinitions {
    String responseString;

    Pet pet = new Pet();

    Pet createdPet;

    Response response;

    @Steps
    PetStepImpl petImpl;

    @Given("The user get the list with all pets")
    public void getPets () {

        responseString = RestAssured.
                given().
                when().get("http://localhost:9966/petclinic/api/pets").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }


    @Then("A list of pets is returned")
    public void aListOfPetsIsReturned () {

        assertTrue(StringUtils.isNotEmpty(responseString));
    }


    @Then("The {} pet is in the response")
    public void verifyCreatedPetInTheList(String name) {

        petImpl.verifyCreatedPet(name);
    }

    @When("The user creates pet with name {}")
    public void createSpecificPet(String name) {
        petImpl.createNewPet(name);
    }


    @And("The {} pet is created")
    public void verifyCreatedPet(String name) {

        responseString = RestAssured.
                given()
                .pathParam("name", name)
                .when().get("http://localhost:9966/petclinic/api/pets/*/name/{name}")
                .then().assertThat().statusCode(200)
                .extract().body().asString();
    }
}
