package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import models.Speciality;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import stepImplementation.DatabaseStepImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class DatabaseStepDefinitions {


  @Steps
  DatabaseStepImpl databaseStepImpl;



  @Given("^the specialities (.*) are deleted from DB$")
  public void deleteSpecialities (String specialities) {

    databaseStepImpl.deleteSpecialities(Arrays.asList(specialities.split(",")));

  }

  @And("^the specialities (.*) are added to DB$")
  public void addSpecialities(String specialities){
    var specs = Arrays.asList((specialities.split(",")));
    for (var spec: specs) {
      databaseStepImpl.addSpecialities(spec);
    }
  }
}
