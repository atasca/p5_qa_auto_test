package stepImplementation;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.*;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import rest.Endpoints;
import util.World;

import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SpecialityStepImpl {

    private Response response;

    public void userRequestAllSpecialities() {
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.SPECIALITIES);
        World.setResponse(response);
    }

    public void updateSpecialityName(String id, String name) {
        Type type = Type.builder().
                id(Integer.parseInt(id))
                .name(name)
                .build();
        RestAssured.given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(type)
                .when()
                .put("http://localhost:9966/petclinic/api/specialties/{id}").
                then().assertThat().statusCode(204);
    }

    public void specialityNameUpdate(String id, String name) {
        String responseString = RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/specialties/{id}").
                then().assertThat().statusCode(200)
                .extract().body().asString();
        assertTrue(responseString.contains(name));
    }

    public void DeleteSpeciality(String id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().delete("http://localhost:9966/petclinic/api/specialties/{id}").
                then().assertThat().statusCode(204)
                .extract().body().asString();
    }

    public void aSpecialityIsDeleted(String id) {
        RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/specialties/{id}").
                then().assertThat().statusCode(404)
                .extract().body().asString();
    }

}
