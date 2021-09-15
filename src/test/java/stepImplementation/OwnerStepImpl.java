package stepImplementation;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Owner;
import models.Pet.Pet;
import models.PetType.Type;
import models.Specialty.Specialty;
import models.Vet.Body;
import models.Vet.GetDBResponse;
import models.Vet.GetVetApiResponse;
import models.Vet.InvalidBody;
import models.Visit.Visit;
import models.owners.body;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.groups.Tuple;
import org.junit.Assert;
import rest.Endpoints;
import util.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.junit.Assert.assertEquals;


public class OwnerStepImpl {

  private Response response;
  private DatabaseStepImpl databaseimpl = new DatabaseStepImpl();
  private body ownerBody = body.builder().build();
  private InvalidBody invalidOwner = InvalidBody.builder().build();
  private ContentType type;
  private String data;
  private String newFirstName;
  private String newLastName ;
  Owner owner = new Owner();


  public void createNewOwner (String firstName, String lastName) {

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
    owner.setPets(pets);

    response = rest()
        .baseUri(Endpoints.BASE_URL)
        .contentType(ContentType.JSON)
        .body(owner)
        .post(Endpoints.OWNERS);

    Assert.assertEquals("create owner request failed", HttpStatus.SC_CREATED, response.statusCode());

    World.setResponse(response);
  }



  public void verifyCreatedOwner (String firstName, String lastName) {

    Owner createOwnerResponse = response.as(Owner.class);

    assertEquals("First name is incorrect", firstName, createOwnerResponse.getFirstName());
    assertEquals("Last name is incorrect", lastName, createOwnerResponse.getLastName());

    JsonPath jsonPath = response.jsonPath();


    assertEquals("First name is incorrect", firstName, jsonPath.getString("firstName"));
    assertEquals("Last name is incorrect", lastName, jsonPath.getString("lastName"));
  }

  public void getOwners() {
    response = rest()
            .baseUri(Endpoints.BASE_URL)
            .contentType(ContentType.JSON)
            .get(Endpoints.OWNERS);
    World.setResponse(response);
  }
  public void setContentType(String contentType) {
    if (Objects.equals(contentType, "json"))
      this.type = ContentType.JSON;
    else {
      if (Objects.equals(contentType, "txt"))
        this.type = ContentType.TEXT;
      else
        this.type = ContentType.JSON;
    }

  }

  public void addOwner(String owner, String contentType, String data) {
    setContentType(contentType);
    List<Specialty> specialties = new ArrayList<>();
    List<String> ownerNames = Arrays.asList(owner.split(","));
    ownerBody.setFirstName(ownerNames.get(0));
    ownerBody.setLastName(ownerNames.get(1));
    List<Pet> pets = new ArrayList<>();
    ownerBody.setPets(pets);
    Object restBody;
    switch (data) {
      case "ownersType":
        this.data = "ownersType";
        ownerBody.setCity("testCity");
        ownerBody.setTelephone("075644323");
        ownerBody.setAddress("testAddress");
        restBody = ownerBody;
        break;
      case "invalidContentType":
        this.data = "invalidContentType";
        restBody = ownerBody.toString();
        break;
      case "invalidBody":
        this.data = "invalidBody";
        ownerBody.setCity("testCity");
        ownerBody.setTelephone("testTelephone");
        ownerBody.setAddress("testAddress");
        restBody = ownerBody;
        break;
      default:
        throw new UnsupportedOperationException();
    }

    response = rest()
            .baseUri(Endpoints.BASE_URL)
            .contentType(this.type)
            .body(restBody)
            .post(Endpoints.OWNERS).then().log().all().and().extract().response();
    World.setResponse(response);
  }

  public void checkNewOwner() {
    SoftAssertions softAssert = new SoftAssertions();
    Response apiResponse = World.getResponse();

    switch (this.data) {
      case "ownersType":
        if (apiResponse.statusCode() == 200) {
          Owner apiOwner = apiResponse.as(Owner.class);
          Integer id = Integer.valueOf(apiOwner.getId());
          Owner dbVet = databaseimpl.getOwnerById(id);

          softAssert.assertThat(apiOwner)
                  .isEqualTo(dbVet);
        } else if (apiResponse.statusCode() == 204) {
          Owner dbOwner = databaseimpl.getOwnerById(Integer.valueOf(World.getOwnerResponse().getId()));
          softAssert.assertThat(Tuple.tuple(dbOwner.getFirstName(), dbOwner.getLastName()))
                  .isEqualTo(Tuple.tuple(this.newFirstName,this.newLastName));
        }
        break;
      case "invalidContentType":
      case "invalidBody":
        softAssert.assertThat(apiResponse.toString())
                .contains("RestAssuredResponseImpl");
        break;
      default:
        throw new UnsupportedOperationException();

    }
    softAssert.assertAll();
  }

  public void updateOwner(String newName, String contentType, String data) {

    Owner dbOwner;

    setContentType(contentType);
    dbOwner = World.getOwnerResponse();

    this.newFirstName = newName;
    this.newLastName = newName;

    Object restBody;
    switch (data) {
      case "ownersType":
        this.data = "ownersType";
        ownerBody.setFirstName(newName);
        ownerBody.setLastName(newName);
        restBody = ownerBody;
        break;
      case "invalidContentType":
        this.data = "invalidContentType";
        restBody = ownerBody.toString();
        break;
      case "invalidBody":
        this.data = "invalidBody";
        restBody = ownerBody;
        break;
      default:
        throw new UnsupportedOperationException();
    }
    response = rest()
            .baseUri(Endpoints.BASE_URL)
            .contentType(this.type)
            .body(restBody)
            .put(Endpoints.OWNERS + "/" + dbOwner.getId()).then().log().all().and().extract().response();
    World.setResponse(response);
  }

  public void deleteOwner() {
    String queryId = "/" + World.getOwnerResponse().getId();
    response = rest()
            .baseUri(Endpoints.BASE_URL)
            .contentType(ContentType.JSON)
            .delete(Endpoints.OWNERS + queryId);
    World.setResponse(response);
  }

  public void deleteNonexistentOwner(String owner) {
    if (Objects.equals(owner, "nonExistentOwnerTest")) {
      int id = databaseimpl.getMaxId("vets") + 1;
      String queryId = "/" + String.valueOf(id);
      response = rest()
              .baseUri(Endpoints.BASE_URL)
              .contentType(ContentType.JSON)
              .delete(Endpoints.OWNERS + queryId);
      World.setResponse(response);
    } else {
      throw new UnsupportedOperationException();

    }
  }

  public void checkDeletedOwner() {
    SoftAssertions softAssert = new SoftAssertions();

    Owner nullOwner = new Owner();
    nullOwner.setId(null);
    nullOwner.setCity(null);
    nullOwner.setAddress(null);
    nullOwner.setLastName(null);
    nullOwner.setLastName(null);
    nullOwner.setPets(null);
    nullOwner.setTelephone(null);
    Owner dbOwner = databaseimpl.getOwnerById(Integer.valueOf(World.getOwnerResponse().getId()));

    softAssert.assertThat(dbOwner)
            .isEqualTo(nullOwner);
    softAssert.assertAll();
  }

  public void verifyInvalidGetById() {
    SoftAssertions softAssert = new SoftAssertions();
    softAssert.assertThat(World.getResponse().asString())
            .isEmpty();
    softAssert.assertAll();
  }

  public void getOwnerById() {
    String query = "/" + World.getOwnerResponse().getId();
    response = rest()
            .baseUri(Endpoints.BASE_URL)
            .contentType(ContentType.JSON)
            .get(Endpoints.OWNERS + query);
    World.setResponse(response);
  }

  public void checkGetByIdOwner() {
    SoftAssertions softAssert = new SoftAssertions();

    Owner apiOwner = World.getResponse().as(Owner.class);
    Owner dbOwner = databaseimpl.getOwnerById(Integer.valueOf(apiOwner.getId()));

    softAssert.assertThat(apiOwner)
            .isEqualTo(dbOwner);
    softAssert.assertAll();
  }
  public void getOwnerById(String owner) {
    if (Objects.equals(owner, "invalidOwnerById")) {
      int id = databaseimpl.getMaxId("vets") + 1;
      String query = "/" + String.valueOf(id);
      response = rest()
              .baseUri(Endpoints.BASE_URL)
              .contentType(ContentType.JSON)
              .get(Endpoints.OWNERS + query);
      World.setResponse(response);
    } else {
      throw new UnsupportedOperationException();

    }
  }
}
