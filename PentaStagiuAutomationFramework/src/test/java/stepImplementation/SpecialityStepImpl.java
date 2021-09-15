package stepImplementation;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.*;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import rest.Endpoints;
import util.World;

import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.junit.Assert.assertEquals;


public class SpecialityStepImpl {

  private Response response;

  Speciality speciality = new Speciality();

  public void getAllSpecialities () {

    response = rest()
            .baseUri(Endpoints.BASE_URL)
            .contentType(ContentType.JSON)
            .body(speciality)
            .post(Endpoints.SPECIALITIS);

    World.setResponse(response);
  }

  public void verifyStatusCode (String statusCode) {
    response=World.getResponse();
    assertEquals("statues code is incorrect", Integer.parseInt(statusCode), response.statusCode());
  }


  public void verifyAnswer() {
    DatabaseStepImpl databaseStep = new DatabaseStepImpl();
    List<Speciality> databaseSpecialities = databaseStep.getAllSpecialities();
    response=World.getResponse();
    SoftAssertions softassert = new SoftAssertions();
    softassert.assertThat(databaseSpecialities)
            .as("Specialities are different")
            .isEqualTo(response);
    softassert.assertAll();
  }

  public void createNewSpeciality(String id, String name) {

    speciality.setId(Integer.parseInt(id));
    speciality.setName(name);

    response = rest()
            .baseUri(Endpoints.BASE_URL)
            .contentType(ContentType.JSON)
            .body(speciality)
            .post(Endpoints.SPECIALITIS);

    World.setResponse(response);
  }

  public void verifyResponse(String expected) {
    response=World.getResponse();
    assertEquals("statues code is incorrect", expected, response.toString());
  }
}
