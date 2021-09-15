package stepImplementation;

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

public class UserStepImpl {

    private Response response;

    User user = new User();
    public void createNewUser(String username) {

        user.setUsername(username);

        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .body(user)
                .post(Endpoints.USERS);

        Assert.assertEquals("create owner request failed", HttpStatus.SC_CREATED, response.statusCode());

        World.setResponse(response);
    }

    public void verifyCreatedUser(String username) {

        User createUserResponse = response.as(User.class);

        assertEquals("Username is incorrect", username, createUserResponse.getUsername());

        JsonPath jsonPath = response.jsonPath();

        assertEquals("Username is incorrect", username, jsonPath.getString("username"));
    }
}
