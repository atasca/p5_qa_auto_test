package stepDefinitions;


import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import util.World;


public class DataValidationStepDefinitions {

  @Given("Thedfsfds user get the list with all owners")
  public void getOwners () {

    World.getResponse();
  }
}
