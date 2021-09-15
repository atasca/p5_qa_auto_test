package stepImplementation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Speciality;
import models.Vet;


import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class VetStepImpl {
    Vet vet = new Vet();

    public void updateVetFirstName(String id, String firstName, List<Map<String, String>> table) {
        for (Map<String, String> columns : table) {
            Speciality speciality = new Speciality();
            speciality.setId(Integer.parseInt(columns.get("specialitiesId")));

            vet = Vet.builder().
                    id(Integer.parseInt(id))
                    .lastName(columns.get("lastName"))
                    .firstName(firstName)
                    .speciality(speciality)
                    .build();
            RestAssured.given()
                    .pathParam("id", id)
                    .contentType(ContentType.JSON)
                    .body(vet)
                    .when()
                    .put("http://localhost:9966/petclinic/api/vets/{id}").
                    then().assertThat().statusCode(204);
        }

    }

    public void vetFirstNameUpdate(String id, String firstName) {
        String responseString = RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/vets/{id}").
                then().assertThat().statusCode(200)
                .extract().body().asString();
        assertTrue(responseString.contains(firstName));
    }

    public void DeleteVet(String id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().delete("http://localhost:9966/petclinic/api/vets/{id}").
                then().assertThat().statusCode(204)
                .extract().body().asString();
    }

    public void aVetIsDeleted(String id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/vets/{id}").
                then().assertThat().statusCode(404)
                .extract().body().asString();
    }
}
