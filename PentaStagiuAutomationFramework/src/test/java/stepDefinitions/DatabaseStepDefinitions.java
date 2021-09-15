package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import stepImplementation.DatabaseStepImpl;

import java.util.Arrays;


public class DatabaseStepDefinitions {


  @Steps
  DatabaseStepImpl databaseStepImpl;


  @Given("^the specialities (.*) are deleted from DB$")
  public void deleteSpecialities (String specialities) {

    databaseStepImpl.deleteSpecialities(Arrays.asList(specialities.split(",")));

  }

  @And("^the specialities (.*) are added to DB$")
  public void addSpecialities(String specialites){

    databaseStepImpl.addSpecialities(Arrays.asList((specialites.split(","))));

  }

}
