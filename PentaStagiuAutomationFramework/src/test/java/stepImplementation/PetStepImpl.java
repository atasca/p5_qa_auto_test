package stepImplementation;

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

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.junit.Assert.assertEquals;


public class PetStepImpl {

    private Response response;

    Pet pet = new Pet();

    public void createNewPet(String name) {
        pet.setName(name);

        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .body(pet)
                .post(Endpoints.PETS);

        Assert.assertEquals("create pet request failed", HttpStatus.SC_CREATED, response.statusCode());

        World.setResponse(response);
    }

    public void verifyCreatedPet(String name) {

        Pet createPetResponse = response.as(Pet.class);

        assertEquals("Name is incorrect", name, createPetResponse.getName());

        JsonPath jsonPath = response.jsonPath();

        assertEquals("Name is incorrect", name, jsonPath.getString("name"));
    }
}
