package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Speciality;
import models.Type;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import stepImplementation.DatabaseStepImpl;
import stepImplementation.SpecialityStepImpl;
import util.World;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class SpecialityStepDefinitions {

    String responseString;

    @Steps
    SpecialityStepImpl specialityStepImpl;
    @Steps
    DatabaseStepImpl databaseImpl;

    @When("the user requests all specialities")
    public void userRequestAllSpecialities() {

        specialityStepImpl.userRequestAllSpecialities();
    }

    @Then("the status code is {}")
    public void verifyGetSpecialitiesRequestHasRightStatusCode(Integer statusCode) {
        Assert.assertEquals("get specialities request passed", statusCode.intValue(), World.getResponse().statusCode());
    }

    @And("verify the get all specialities response with DB")
    public void checkResponseWithDb() {
        List<Speciality> specialitiesList = databaseImpl.getAllSpecialities();
        Speciality[] db_specialities = new Speciality[specialitiesList.size()];
        specialitiesList.toArray(db_specialities);

        Speciality[] api_specialities = World.getResponse().then().extract().body().as(Speciality[].class);

        SoftAssertions softAssertion = new SoftAssertions();
        softAssertion.assertThat(db_specialities)
                .as("specialities are equal")
                .isEqualTo(api_specialities);
    }

    @When("The user get the speciality with id {}")
    public void getSpecialityId(String id) {
        responseString = RestAssured.
                given()
                .pathParam("id", id).
                when().get("http://localhost:9966/petclinic/api/specialties/{id}").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }

    @Then("A speciality is returned")
    public void aPetIsReturned() {

        assertTrue(StringUtils.isNotEmpty(responseString));
    }

    @When("The user updates speciality that have id {} with new name {}")
    public void updateSpecialityName(String id, String name) {

        specialityStepImpl.updateSpecialityName(id, name);
    }

    @Then("The new name of the speciality with id {} is updated with {}")
    public void specialityNameUpdate(String id, String name) {
        specialityStepImpl.specialityNameUpdate(id, name);
    }

    @When("The user get delete speciality with id {}")
    public void DeleteSpeciality(String id) {
        specialityStepImpl.DeleteSpeciality(id);
    }

    @Then("The speciality with id {} is deleted")
    public void aSpecialityIsDeleted(String id) {
        specialityStepImpl.aSpecialityIsDeleted(id);
    }

}
