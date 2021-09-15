package stepImplementation;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Owner;
import models.Pet;
import models.Type;
import models.Visit;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import rest.Endpoints;
import util.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class OwnerStepImpl {

    private Response response;

    Owner owner = new Owner();

    public void userRequestAllOwners() {
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.OWNERS);
        World.setResponse(response);
    }


    public void createNewOwner(String firstName, String lastName) {

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


    public void verifyCreatedOwner(String firstName, String lastName) {

        Owner createOwnerResponse = response.as(Owner.class);

        assertEquals("First name is incorrect", firstName, createOwnerResponse.getFirstName());
        assertEquals("Last name is incorrect", lastName, createOwnerResponse.getLastName());

        JsonPath jsonPath = response.jsonPath();


        assertEquals("First name is incorrect", firstName, jsonPath.getString("firstName"));
        assertEquals("Last name is incorrect", lastName, jsonPath.getString("lastName"));
    }

    public void verifyCreatedOwner(String lastName) {

        RestAssured.
                given()
                .pathParam("lastName", lastName)
                .when().get("http://localhost:9966/petclinic/api/owners/*/lastname/{lastName}")
                .then().assertThat().statusCode(200)
                .extract().body().asString();
    }

    public void theUserCreatesANewOwner(List<Map<String, String>> table) {

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


    public void updateOwnerFirstName(String id, String firstName, List<Map<String, String>> table) {
        for (Map<String, String> columns : table) {
            List<Pet> pets = new ArrayList<>();
            Pet pet = new Pet();
            pet.setId(Integer.valueOf(columns.get("petsId")));
            pets.add(pet);

            owner = Owner.builder()
                    .id(String.valueOf(id))
                    .firstName(firstName)
                    .lastName(columns.get("lastName"))
                    .address(columns.get("address"))
                    .city(columns.get("city"))
                    .telephone(columns.get("telephone"))
                    .pets(pets)
                    .build();
            RestAssured.given()
                    .pathParam("id", id)
                    .contentType(ContentType.JSON)
                    .body(owner)
                    .when()
                    .put("http://localhost:9966/petclinic/api/owners/{id}").
                    then().assertThat().statusCode(204);

        }
    }

    public void petNameUpdate(String id, String name) {
        String responseString = RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/owners/{id}").
                then().assertThat().statusCode(200)
                .extract().body().asString();
        assertTrue(responseString.contains(name));
    }

    public void DeleteUser(String id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().delete("http://localhost:9966/petclinic/api/owners/{id}").
                then().assertThat().statusCode(204)
                .extract().body().asString();
    }

    public void aOwnerIsDeleted(String id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/owners/{id}").
                then().assertThat().statusCode(404)
                .extract().body().asString();
    }
}

