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

public class VisitStepImpl {

    private Response response;

    Visit visit = new Visit();
    public void createNewVisit(String description) {
        visit.setDescription(description);

        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .body(visit)
                .post(Endpoints.VISIT);

        Assert.assertEquals("create owner request failed", HttpStatus.SC_CREATED, response.statusCode());

        World.setResponse(response);
    }

    public void verifyCreatedOwner(String description) {
        Visit createdVisitResponse = response.as(Visit.class);

        assertEquals("Description is incorrect", description, createdVisitResponse.getDescription());

        JsonPath jsonPath = response.jsonPath();


        assertEquals("Description is incorrect", description, jsonPath.getString("description"));
    }
}
