package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import stepImplementation.DatabaseStepImpl;
import stepImplementation.PetTypeStepImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetTypeStepDefinitions {
    ArrayList<String> types = new ArrayList<>();
    @Steps
    PetTypeStepImpl petTypeStepImpl;
    private String tableName = "types";


    @When("^the user requests all pet types$")
    public void getTypes() {
        petTypeStepImpl.getTypes();
    }

    @And("^verify the get all pet types response with DB$")
    public void checkResponse() {
        petTypeStepImpl.checkResponse();
    }

    @When("^the user creates new pet (.*) with (.*) having (.*)$")
    public void createType(String type, String contentType, String data) {
        petTypeStepImpl.addType(type, contentType, data);
        List<String> typeNames = Arrays.asList(type.split(","));
        types.add(typeNames.get(0));
        types.add(type);
    }

    @And("^verify the pet type response with DB$")
    public void checkNewType() {
        petTypeStepImpl.checkType();
    }

    @When("^the user updates pet type with (.*) and (.*) having (.*)$")
    public void updateType(String newName, String contentType, String data) {
        petTypeStepImpl.updateType(newName, contentType, data);
        types.add(newName);
    }

    @When("^The user requests to delete the pet types$")
    public void deleteType() {
        petTypeStepImpl.deleteType();
    }

    @And("^the pet type shouldn't be in bd$")
    public void checkDeletedType() {
        petTypeStepImpl.checkDeletedType();
    }

    @When("^The user wants to delete the pet (.*)")
    public void deleteNonexistentType(String type) {
        petTypeStepImpl.deleteNonexistentType(type);
    }

    @And("^the pet type response should be null$")
    public void verifyInvalidGetById() {
        petTypeStepImpl.verifyInvalidGetById();
    }

    @When("^the user requests the pet (.*) by id$")
    public void getTypeById(String type) {
        petTypeStepImpl.getTypeById();
        types.add(type);
    }

    @And("^verify the get pet type response with DB$")
    public void checkTypeWithDb() {
        petTypeStepImpl.checkGetByIdType();
    }

    @When("^the user wants the pet (.*) by id$")
    public void getInvalidTypeById(String type) {
        petTypeStepImpl.getTypeById(type);
    }

    @After("@DeleteTypeData")
    public void deleteVets() {
        DatabaseStepImpl databaseStepImpl = new DatabaseStepImpl();
        databaseStepImpl.deleteFromTabe(tableName ,types);
    }

}
