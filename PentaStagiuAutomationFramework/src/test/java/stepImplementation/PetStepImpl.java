package stepImplementation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Owner;
import models.Pet;
import models.Type;
import models.Visit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class PetStepImpl {
    Pet pet = new Pet();
    public Type type;

    public void theUserCreatesANewPet(List<Map<String, String>> table) {
        for (Map<String, String> columns : table) {
            Owner owner = new Owner();
            Type type = new Type();
            owner.setId(String.valueOf(columns.get("ownerId")));
            type.setId(Integer.parseInt(columns.get("typeId")));
            List<Visit> visits = new ArrayList<>();
            Visit visit = new Visit();
            visits.add(visit);

            this.pet = Pet.builder()
                    .birthDate(columns.get("birthDate"))
                    .name(columns.get("name"))
                    .owner(owner)
                    .type(type)
                    .visits(visits)
                    .build();
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(pet)
                    .when()
                    .post("http://localhost:9966/petclinic/api/pets")
                    .then().log().all().statusCode(201);
        }
    }

    public void verifyCreatedPet(String name) {
        String responseString = RestAssured.
                given()
                .when().get("http://localhost:9966/petclinic/api/pets")
                .then().assertThat().statusCode(200)
                .extract().body().asString();
        assertTrue(responseString.contains(name));
    }

    public void updatePetName(String id, String name, List<Map<String, String>> table) {
        for (Map<String, String> columns : table) {
            Owner owner = new Owner();
            Type type = new Type();
            owner.setId(String.valueOf(columns.get("ownerId")));
            type.setId(Integer.parseInt(columns.get("typeId")));
            List<Visit> visits = new ArrayList<>();
            Visit visit = new Visit();
            visits.add(visit);

            pet = Pet.builder().
                    id(Integer.parseInt(id))
                    .birthDate(columns.get("birthDate"))
                    .name(name)
                    .owner(owner)
                    .type(type)
                    .visits(visits)
                    .build();
            RestAssured.given()
                    .pathParam("id", id)
                    .contentType(ContentType.JSON)
                    .body(pet)
                    .when()
                    .put("http://localhost:9966/petclinic/api/pets/{id}").
                    then().assertThat().statusCode(204);
        }
    }

    public void petNameUpdate(String id, String name) {
        String responseString = RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/pets/{id}").
                then().assertThat().statusCode(200)
                .extract().body().asString();
        assertTrue(responseString.contains(name));
    }

    public void DeletePet(String id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().delete("http://localhost:9966/petclinic/api/pets/{id}").
                then().assertThat().statusCode(204)
                .extract().body().asString();
    }

    public void aPetIsDeleted(String id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/pets/{id}").
                then().assertThat().statusCode(404)
                .extract().body().asString();
    }

    public void theUserCreatesANewPetTypes(List<Map<String, String>> table) {
        for (Map<String, String> columns : table) {

            this.type = Type.builder()
                    .id(Integer.parseInt(columns.get("petId")))
                    .name(columns.get("petName"))
                    .build();
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(type)
                    .when()
                    .post("http://localhost:9966/petclinic/api/pettypes")
                    .then().log().all().statusCode(201);
        }
    }

    public void verifyCreatedPetType(String name) {
        String responseString = RestAssured.
                given()
                .when().get("http://localhost:9966/petclinic/api/pettypes")
                .then().assertThat().statusCode(200)
                .extract().body().asString();
        assertTrue(responseString.contains(name));
    }

    public void updatePetTypeName(String id, String name) {
        type = Type.builder().
                id(Integer.parseInt(id))
                .name(name)
                .build();
        RestAssured.given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(type)
                .when()
                .put("http://localhost:9966/petclinic/api/pettypes/{id}").
                then().assertThat().statusCode(204);
    }

    public void petTypeNameUpdate(String id, String name) {
        String responseString = RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/pettypes/{id}").
                then().assertThat().statusCode(200)
                .extract().body().asString();
        assertTrue(responseString.contains(name));
    }

    public void DeletePetType(String id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().delete("http://localhost:9966/petclinic/api/pettypes/{id}").
                then().assertThat().statusCode(204)
                .extract().body().asString();
    }

    public void aPetTypeIsDeleted(String id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/pettypes/{id}").
                then().assertThat().statusCode(404)
                .extract().body().asString();
    }
}

