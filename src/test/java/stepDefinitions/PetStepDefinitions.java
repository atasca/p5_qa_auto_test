package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import stepImplementation.DatabaseStepImpl;
import stepImplementation.PetStepImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetStepDefinitions {

    ArrayList<String> pets = new ArrayList<>();
    @Steps
    PetStepImpl petStepImpl;
    private String tableName = "pets";


    @When("^the user requests all pets$")
    public void getPets() {
        petStepImpl.getPet();
    }

    @And("^verify the get all pets response with DB$")
    public void checkResponse() {
        petStepImpl.checkResponse();
    }

    @When("^the user creates new pets (.*) with (.*) having (.*)$")
    public void createPet(String pet, String contentType, String data) {
        petStepImpl.addPet(pet, contentType, data);
        List<String> petNames = Arrays.asList(pet.split(","));
        pets.add(petNames.get(0));
        pets.add(pet);
    }

    @When("^the user updates pet with (.*) and (.*) having (.*)$")
    public void updatePet(String newName, String contentType, String data) {
        petStepImpl.updatePet(newName, contentType, data);
        pets.add(newName);
    }

    @And("^verify the pet response with DB$")
    public void checkNewPet() {
        petStepImpl.checkPet();
    }

    @When("^The user requests to delete the pet$")
    public void deletePet() {
        petStepImpl.deletePet();
    }

    @And("^the pet shouldn't be in bd$")
    public void checkDeletedPet() {
        petStepImpl.checkDeletedPet();
    }

    @When("^The user wants to delete the pets (.*)$")
    public void deleteNonexistentPet(String pet) {
        petStepImpl.deleteNonexistentPet(pet);
    }

    @And("^the pet response should be null$")
    public void verifyInvalidGetById() {
        petStepImpl.verifyInvalidGetById();
    }

    @When("^the user requests the pets (.*) by id$")
    public void getPetById(String pet) {
        petStepImpl.getPetById();
        pets.add(pet);
    }

    @And("^verify the get pet response with DB$")
    public void checkPetWithDb() {
        petStepImpl.checkGetByIdPet();
    }

    @When("^the user wants the pets (.*) by id$")
    public void getInvalidPetById(String pet) {
        petStepImpl.getPetById(pet);
    }



    @After("@DeletePetData")
    public void deletePets() {
        DatabaseStepImpl databaseStepImpl = new DatabaseStepImpl();
        databaseStepImpl.deleteFromTabe(tableName ,pets);
    }
}
