package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;
import stepImplementation.PetStepImpl;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class PetStepDefinitions {
    String responseString;


    @Steps
    PetStepImpl petImplementation;

    @Given("The user get the list with all pets")
    public void getPet() {
        responseString = RestAssured.
                given().
                when().get("http://localhost:9966/petclinic/api/pets").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }

    @Then("A list of pets is returned")
    public void aListOfPetsIsReturned() {
        assertTrue(StringUtils.isNotEmpty(responseString));
    }

    @When("The user creates a new pet")
    public void theUserCreatesANewPet(List<Map<String, String>> table) {
        petImplementation.theUserCreatesANewPet(table);
    }

    @Then("The {} pet is created")
    public void verifyCreatedPet(String name) {
        petImplementation.verifyCreatedPet(name);
    }

    @When("The user get the pet with id {}")
    public void getPetId(String id) {
        responseString = RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/pets/{id}").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }

    @Then("A pet is returned")
    public void aPetIsReturned() {

        assertTrue(StringUtils.isNotEmpty(responseString));
    }

    @When("The user updates pet that have id {} with new name {}")
    public void updatePetName(String id, String name, List<Map<String, String>> table) {

        petImplementation.updatePetName(id, name, table);
    }

    @Then("The pet name with id {} is updated with {}")
    public void petNameUpdate(String id, String name) {

        petImplementation.petNameUpdate(id, name);
    }

    @When("The user get delete pet with id {}")
    public void DeletePet(String id) {

        petImplementation.DeletePet(id);
    }

    @Then("The pet with id {} is deleted")
    public void aPetIsDeleted(String id) {

        petImplementation.aPetIsDeleted(id);
    }

    @When("The user get the list with all pets types")
    public void getPetTypes() {
        responseString = RestAssured.
                given().
                when().get("http://localhost:9966/petclinic/api/pets/pettypes").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }

    @Then("A list of pets types is returned")
    public void aListOfPetsTypesIsReturned() {

        assertTrue(StringUtils.isNotEmpty(responseString));
    }

    @When("The user creates a new pet type")
    public void theUserCreatesANewPetTypes(List<Map<String, String>> table) {

        petImplementation.theUserCreatesANewPetTypes(table);
    }

    @Then("The {} pet type is created")
    public void verifyCreatedPetType(String name) {
        petImplementation.verifyCreatedPetType(name);
    }

    @When("The user get the pet type with id {}")
    public void getPetTypeId(String id) {
        responseString = RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/pettypes/{id}").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }

    @Then("A pet type is returned")
    public void aPeTypeIsReturned() {
        assertTrue(StringUtils.isNotEmpty(responseString));
    }

    @When("The user updates pet type that have id {} with new name {}")
    public void updatePetTypeName(String id, String name) {
        petImplementation.updatePetTypeName(id, name);
    }

    @Then("The new name of the pet type with id {} is updated with {}")
    public void petTypeNameUpdate(String id, String name) {

        petImplementation.petTypeNameUpdate(id, name);
    }

    @When("The user get delete pet type with id {}")
    public void DeletePetType(String id) {
      petImplementation.DeletePetType(id);
    }

    @Then("The pet type with id {} is deleted")
    public void aPetTypeIsDeleted(String id) {
        petImplementation.aPetTypeIsDeleted(id);
    }

}



