package stepImplementation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Owner;
import models.Pet;
import models.Type;
import models.Visit;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class VisitStepImpl {
    private Visit visit;

    public void theUserCreatesANewVisit(List<Map<String, String>> table) {
        for (Map<String, String> columns : table) {
            Pet pet = new Pet();
            pet.setId(Integer.parseInt(columns.get("petId")));
            pet.setBirthDate(String.valueOf(columns.get("petBirthDate")));
            Owner owner = new Owner();
            owner.setId(String.valueOf(columns.get("petOwnerId")));
            pet.setOwner(owner);
            Type type = new Type();
            type.setId(Integer.parseInt(columns.get("typeId")));
            pet.setType(type);


            this.visit = Visit.builder()
                    .date(columns.get("date"))
                    .description(columns.get("description"))
                    .pet(pet)
                    .build();
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(visit)
                    .when()
                    .post("http://localhost:9966/petclinic/api/visits")
                    .then().log().all().statusCode(201);
        }
    }

    public void verifyCreatedvisit(String description) {
        String responseString = RestAssured.
                given()
                .when().get("http://localhost:9966/petclinic/api/visits")
                .then().assertThat().statusCode(200)
                .extract().body().asString();
        assertTrue(responseString.contains(description));
    }

    public void updateVisitDescription(Integer id, String description, List<Map<String, String>> table) {
        for (Map<String, String> columns : table) {
            Pet pet = new Pet();
            pet.setId(Integer.parseInt(columns.get("petId")));
            pet.setBirthDate(String.valueOf(columns.get("petBirthDate")));
            Owner owner = new Owner();
            owner.setId(String.valueOf(columns.get("petOwnerId")));
            pet.setOwner(owner);
            Type type = new Type();
            type.setId(Integer.parseInt(columns.get("typeId")));
            pet.setType(type);

            this.visit = Visit.builder()
                    .date(columns.get("date"))
                    .description(description)
                    .pet(pet)
                    .id(id)
                    .build();
            RestAssured.given()
                    .pathParam("id", id)
                    .contentType(ContentType.JSON)
                    .body(visit)
                    .when()
                    .put("http://localhost:9966/petclinic/api/visits/{id}")
                    .then().log().all().statusCode(204);
        }
    }

    public void visitDescriptionUpdate(Integer id, String description) {
        String responseString = RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/visits/{id}").
                then().assertThat().statusCode(200)
                .extract().body().asString();
        assertTrue(responseString.contains(description));
    }

    public void DeleteVisit(Integer id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().delete("http://localhost:9966/petclinic/api/visits/{id}").
                then().assertThat().statusCode(204)
                .extract().body().asString();
    }

    public void aVisitIsDeleted(Integer id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/visits/{id}").
                then().assertThat().statusCode(404)
                .extract().body().asString();
    }
}
