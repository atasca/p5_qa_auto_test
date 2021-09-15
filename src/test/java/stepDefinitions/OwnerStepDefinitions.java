package stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Owner;
import models.Pet.Pet;
import models.PetType.Type;
import models.Visit.Visit;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import stepImplementation.DatabaseStepImpl;
import stepImplementation.OwnerStepImpl;
import util.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class OwnerStepDefinitions {

  String responseString;

  Owner owner = new Owner();

  Owner createdOwner;

  Response response;
  ArrayList<String> owners = new ArrayList<>();

  private String tableName = "owners";

  @Steps
  OwnerStepImpl ownerImpl;
  DatabaseStepImpl databaseStepImpl= new DatabaseStepImpl();


  @Given("The user get the list with all owners")
  public void getOwners2 () {

    responseString = RestAssured.
        given().
        when().get("http://localhost:9966/petclinic/api/owners").
        then().assertThat().statusCode(201)
        .extract().body().asString();
  }


  @Then("A list of owners is returned")
  public void aListOfOwnersIsReturned () {

    SoftAssertions softAssert = new SoftAssertions();
    softAssert.assertThat(World.getResponse())
            .isNotNull();
    softAssert.assertAll();
  }

  @When("^the user requests all owners$")
  public void getOwners() {
    ownerImpl.getOwners();
  }

  @When("^the user creates new owners (.*) with (.*) having (.*)$")
  public void createOwner(String owner, String contentType, String data) {
    ownerImpl.addOwner(owner, contentType, data);
    List<String> ownerNames = Arrays.asList(owner.split(","));
    owners.add(ownerNames.get(0));
    owners.add(owner);
  }

  @When("^the user updates owners with (.*) and (.*) having (.*)$")
  public void updateOwner(String newName, String contentType, String data) {
    ownerImpl.updateOwner(newName, contentType, data);
    owners.add(newName);
  }

  @When("^The user requests to delete the owners$")
  public void deleteOwner1() {
    ownerImpl.deleteOwner();
  }
  @When("^The user wants to delete the owners (.*)")
  public void deleteNonexistentVet(String owner) {
    ownerImpl.deleteNonexistentOwner(owner);
  }


  @And("^the owners shouldn't be in bd$")
  public void checkDeletedOwner() {
    ownerImpl.checkDeletedOwner();
  }


  @And("^the owners response should be null$")
  public void verifyInvalidGetById() {
    ownerImpl.verifyInvalidGetById();
  }

  @When("^the user requests the owners (.*) by id$")
  public void getOwnerById(String vet) {
    ownerImpl.getOwnerById();
    owners.add(vet);
  }

  @And("^verify the get owners response with DB$")
  public void checkOwnerWithDb() {
    ownerImpl.checkGetByIdOwner();
  }

  @When("^the user wants the owners (.*) by id$")
  public void getInvalidVetById(String owner) {
    ownerImpl.getOwnerById(owner);
  }





  @And("^verify the response of owners with DB$")
  public void checkNewOwner() {
    ownerImpl.checkNewOwner();
  }


  @When("The user creates a new owner")
  public void theUserCreatesANewOwner (List<Map<String, String>> table) {

    for (Map<String, String> columns : table) {
      List<Pet> pets = new ArrayList<>();
      Pet pet = new Pet();
      Type type = new Type();
      pet.setName(columns.get("petName"));
      pet.setType(type);
      List<Visit> visits = new ArrayList<>();
      Visit visit = new Visit();
      visits.add(visit);
      pet.setVisits(visits);
      pets.add(pet);

      this.owner = Owner.builder().lastName(columns.get("lastName"))
          .address(columns.get("address"))
          .city(columns.get("city"))
          .firstName(columns.get("firstName"))
          .telephone(columns.get("telephone"))
          .pets(pets)
          .build();
      RestAssured.given()
          .contentType(ContentType.JSON)
          .body(owner)
          .when()
          .post("http://localhost:9966/petclinic/api/owners")
          .then().log().all().statusCode(201);
    }
  }


  @When("The user creates owner with firstName {} and lastName {}")
  public void createNewSpecificUser (String firstName, String lastName) {

    ownerImpl.createNewOwner(firstName, lastName);
  }


  @Then("The {} {} owner is in the response")
  public void verifyCreatedUserInTheList (String firstName, String lastName) {

    ownerImpl.verifyCreatedOwner(firstName, lastName);
  }


  @Then("The new {} {} owner is in the response")
  public void verifyCreatedUserInTheList2 (String firstName, String lastName) {

    assertTrue(responseString.contains(firstName));
    assertTrue(responseString.contains(lastName));
  }


  @Then("The {} owner is created")
  public void verifyCreatedOwner (String lastName) {

    responseString = RestAssured.
        given()
        .pathParam("lastName", lastName)
        .when().get("http://localhost:9966/petclinic/api/owners/*/lastname/{lastName}")
        .then().assertThat().statusCode(200)
        .extract().body().asString();
  }


  @When("The user creates a new owner with firstName {} and lastName {} second example")
  public void createNewSpecificUser2 (String firstName, String lastName) {

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
  public void theLastNameOwnerIsCreatedSecondExample (String lastName) {

    assertTrue(createdOwner.getFirstName().equals(owner.getFirstName()));
  }
  @After("@DeleteOwnerData")
  public void deleteOwner() {
    DatabaseStepImpl databaseStepImpl = new DatabaseStepImpl();
    databaseStepImpl.deleteFromTabe(tableName ,owners);
  }
  @After("@DeleteOwnerData")
  public void deleteOwners() {
    databaseStepImpl.deleteFromTabe(tableName, owners);
  }
}
