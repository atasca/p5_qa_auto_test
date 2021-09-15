package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import stepImplementation.SpecialityStepImpl;
import util.World;


public class SpecialityStepDefinitions {

  Response response;

  @Steps
  SpecialityStepImpl specialityStepImpl;

  @When("the user requests all specialities")
  public void requestAllSpecialities(){
    specialityStepImpl.getAllSpecialities();
  }

  @Then("The status code is {}")
  public void verifyStatusCode (String statusCode) {

    specialityStepImpl.verifyStatusCode(statusCode);
  }

  @And("verify the get all specialities response with DB")
  public void verifySpecialitiesResponseWithDB(){
    specialityStepImpl.verifyAnswer();
  }

  @When("The user creates speciality with id {} and name {}")
  public void createNewSpecificUser (String id, String name) {

    specialityStepImpl.createNewSpeciality(id, name);
  }

  @Then("The returned status code is corresponding")
  public void theReturnedStatusCodeIsCorresponding() {
    specialityStepImpl.verifyStatusCode("400");
  }

  @And("Verify response")
  public void verifyResponse() {
    specialityStepImpl.verifyResponse("Bad Request");
  }

  @Then("the status code is {}")
  public void theStatusCodeIsStatusCode(String statusCode) {
    specialityStepImpl.verifyStatusCode(statusCode);
  }
}
