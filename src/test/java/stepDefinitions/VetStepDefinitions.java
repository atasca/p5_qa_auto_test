package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import stepImplementation.DatabaseStepImpl;
import stepImplementation.VetStepImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VetStepDefinitions {

    ArrayList<String> vets = new ArrayList<>();
    @Steps
    VetStepImpl vetStepImpl;
    private String tableName = "vets";


    @When("^the user requests all vets$")
    public void getVets() {
        vetStepImpl.getVet();
    }

    @And("^verify the get all vets response with DB$")
    public void checkResponse() {
        vetStepImpl.checkResponse();
    }

    @When("^the user creates new vet (.*) with (.*) having (.*)$")
    public void createVet(String vet, String contentType, String data) {
        vetStepImpl.addVet(vet, contentType, data);
        List<String> vetNames = Arrays.asList(vet.split(","));
        vets.add(vetNames.get(0));
        vets.add(vet);
    }

    @When("^the user updates vet with (.*) and (.*) having (.*)$")
    public void updateVet(String newName, String contentType, String data) {
        vetStepImpl.updateVet(newName, contentType, data);
        vets.add(newName);
    }

    @And("^verify the response of vet with DB$")
    public void checkNewVet() {
        vetStepImpl.checkVet();
    }

    @When("^The user requests to delete the vet$")
    public void deleteVet() {
        vetStepImpl.deleteVet();
    }
    @When("^The user wants to delete the vet (.*)")
    public void deleteNonexistentVet(String vet) {
        vetStepImpl.deleteNonexistentVet(vet);
    }


    @And("^the vet shouldn't be in bd$")
    public void checkDeletedVet() {
        vetStepImpl.checkDeletedVet();
    }


    @And("^the vet response should be null$")
    public void verifyInvalidGetById() {
        vetStepImpl.verifyInvalidGetById();
    }

    @When("^the user requests the vet (.*) by id$")
    public void getVetById(String vet) {
        vetStepImpl.getVetById();
        vets.add(vet);
    }

    @And("^verify the get vet response with DB$")
    public void checkVetWithDb() {
        vetStepImpl.checkGetByIdVet();
    }

    @When("^the user wants the vet (.*) by id$")
    public void getInvalidVetById(String vet) {
        vetStepImpl.getVetById(vet);
    }



    @After("@DeleteVetData")
    public void deleteVets() {
        DatabaseStepImpl databaseStepImpl = new DatabaseStepImpl();
        databaseStepImpl.deleteFromTabe(tableName ,vets);
    }

}
