package stepImplementation;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Pet;
import models.Type;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import rest.Endpoints;
import util.World;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.junit.Assert.assertEquals;

public class PetTypeStepImpl {

    private Response response;

    Type type = new Type();

    public void createNewPetType(String name) {


        type.setName(name);

        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .body(type)
                .post(Endpoints.PETTYPES);

        Assert.assertEquals("create pettype request failed", HttpStatus.SC_CREATED, response.statusCode());

        World.setResponse(response);
    }

    public void verifyCreatedPetType(String name) {

        Type createPetTypeResponse = response.as(Type.class);

        assertEquals("Name is incorrect", name, createPetTypeResponse.getName());

        JsonPath jsonPath = response.jsonPath();

        assertEquals("Name is incorrect", name, jsonPath.getString("name"));

    }
}
