package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.*;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;

import stepImplementation.OwnerStepImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class OwnerStepDefinitions {

    String responseString;

    Owner owner = new Owner();

    Owner createdOwner;


    @Steps
    OwnerStepImpl ownerImpl;


    @Given("The user get the list with all owners")
    public void getOwners() {
        responseString = RestAssured.
                given().
                when().get("http://localhost:9966/petclinic/api/owners").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }

    @Then("A list of owners is returned")
    public void aListOfOwnersIsReturned() {

        assertTrue(StringUtils.isNotEmpty(responseString));
    }

    @When("The user creates a new owner")
    public void theUserCreatesANewOwner(List<Map<String, String>> table) {
        ownerImpl.theUserCreatesANewOwner(table);
    }


    @When("The user creates owner with firstName {} and lastName {}")
    public void createNewSpecificUser(String firstName, String lastName) {

        ownerImpl.createNewOwner(firstName, lastName);
    }


    @Then("The {} {} owner is in the response")
    public void verifyCreatedUserInTheList(String firstName, String lastName) {

        ownerImpl.verifyCreatedOwner(firstName, lastName);
    }


    @Then("The new {} {} owner is in the response")
    public void verifyCreatedUserInTheList2(String firstName, String lastName) {

        assertTrue(responseString.contains(firstName));
        assertTrue(responseString.contains(lastName));
    }


    @Then("The {} owner is created")
    public void verifyCreatedOwner(String lastName) {

        ownerImpl.verifyCreatedOwner(lastName);
    }

    @When("The user creates a new owner with firstName {} and lastName {} second example")
    public void createNewSpecificUser2(String firstName, String lastName) {

        owner = new Owner();
        List<Pet> pets = new ArrayList<>();
        Pet pet = new Pet();
        Type type = new Type();
        List<Visit> visits = new ArrayList<>();
        Visit visit = new Visit();
        visits.add(visit);
        pet.setVisits(visits);
        pet.setType(type);
        pets.add(pet);
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        createdOwner = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(owner)
                .when()
                .post("http://localhost:9966/petclinic/api/owners")
                .then().log().all().statusCode(201).extract().body().as(Owner.class);
    }


    @And("The new {} owner is created - second example")
    public void theLastNameOwnerIsCreatedSecondExample(String lastName) {

        assertTrue(createdOwner.getFirstName().equals(owner.getFirstName()));
    }

    @When("The user updates owner that have id {} with new first name {}")
    public void updateOwnerFirstName(String id, String firstName, List<Map<String, String>> table) {

        ownerImpl.updateOwnerFirstName(id, firstName, table);
    }

    @Then("The owner first name with id {} is updated with {}")
    public void petNameUpdate(String id, String name) {

        ownerImpl.petNameUpdate(id, name);
    }

    @When("The user delete owner with id {}")
    public void DeleteUser(String id) {

        ownerImpl.DeleteUser(id);
    }

    @Then("The user with id {} is deleted")
    public void aOwnerIsDeleted(String id) {

        ownerImpl.aOwnerIsDeleted(id);
    }
}
