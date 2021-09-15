package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang3.StringUtils;

import static org.junit.Assert.assertTrue;

public class WebStepDefinitions {

    String responseString;

    Response response;

    @Given("The user get the list with all the links")
    public void getLinks () {

        responseString = RestAssured.
                given().
                when().get("http://localhost:9966/petclinic/api/actuator").
                then().assertThat().statusCode(200)
                .extract().body().asString();
    }


    @Then("A list of links is returned")
    public void aListOfLinkssIsReturned () {

        assertTrue(StringUtils.isNotEmpty(responseString));
    }
}
