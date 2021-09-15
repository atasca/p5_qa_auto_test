package stepDefinitions;


import io.cucumber.java.After;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import stepImplementation.DatabaseStepImpl;
import stepImplementation.SpecialityStepImpl;


import java.util.ArrayList;


public class SpecialityStepDefinitions {

    ArrayList<String> specialties = new ArrayList<>();
    @Steps
    SpecialityStepImpl specialityStepImpl;


    @When("^the user requests all specialities$")
    public void getSpecialties() {
        specialityStepImpl.getSpecialties();
    }

    @And("^verify the get all specialities response with DB$")
    public void checkResponse() {
        specialityStepImpl.checkResponse();
    }

    @When("^the user creates new specialty (.*) with (.*) having (.*)$")
    public void createSpecialty(String specialty, String contentType, String data) {
        specialityStepImpl.addSpecialties(specialty, contentType, data);
        specialties.add(specialty);

    }

    @When("^the user updates specialty with (.*) and (.*) having (.*)$")
    public void updateSpecialty(String newName, String contentType, String data) {
        specialityStepImpl.updateSpecialty(newName, contentType, data);
        specialties.add(newName);
    }

    @And("^verify the response with DB$")
    public void checkNewSpecialty() {
        specialityStepImpl.checkSpecialty();
    }

    @When("^The user requests to delete the specialty$")
    public void deleteSpecialty() {
        specialityStepImpl.deleteSpecialty();
    }

    @And("^the specialty shouldn't be in bd$")
    public void checkDeletedSpecialty() {
        specialityStepImpl.checkDeletedSpecialty();
    }

    @When("^the user requests the specialty (.*) by id$")
    public void getSpecialtyById(String specialty) {
        specialityStepImpl.getSpecialtyById();
        specialties.add(specialty);
    }

    @And("^verify the get speciality response with DB$")
    public void checkSpecialityWithDb() {
        specialityStepImpl.checkGetByIdSpecialty();
    }

    @When("^the user wants the speciality (.*) by id$")
    public void getInvalidSpecialtyById(String specialty) {
        specialityStepImpl.getSpecialtyById(specialty);
    }


    @And("^the response should be null$")
    public void verifyInvalidGetById() {
        specialityStepImpl.verifyInvalidGetById();
    }

    @When("^The user wants to delete the specialty (.*)")
    public void deleteNonexistentSpecialty(String specialty) {
        specialityStepImpl.deleteNonexistentSpecialty(specialty);
    }

    @After("@DeleteData")
    public void deleteSpecialties() {
        DatabaseStepImpl dbDef = new DatabaseStepImpl();
        dbDef.deleteSpecialities(specialties);
    }


}
