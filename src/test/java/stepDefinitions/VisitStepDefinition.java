package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import stepImplementation.DatabaseStepImpl;
import stepImplementation.VisitStepImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisitStepDefinition {

    ArrayList<String> visits = new ArrayList<>();
    @Steps
    VisitStepImpl visitStepImpl;
    private String tableName = "visits";


    @When("^the user requests all visits")
    public void getVets() {
        visitStepImpl.getVet();
    }

    @When("^the user creates new visit (.*) with (.*) having (.*)$")
    public void createVet(String visit, String contentType, String data) {
        visitStepImpl.addVisit(visit, contentType, data);
        List<String> visitNames = Arrays.asList(visit.split(","));
        visits.add(visitNames.get(0));
        visits.add(visit);
    }

    @And("^verify the response of visit with DB$")
    public void checkNewVisit() {
        visitStepImpl.checkVisit();
    }

    @When("^the user updates visit with (.*) and (.*) having (.*)$")
    public void updateVisit(String description, String contentType, String data) {
        visitStepImpl.updateVisit(description, contentType, data);
        visits.add(description);
    }

    @When("^The user requests to delete the visit$")
    public void deleteVisit() {
        visitStepImpl.deleteVisit();
    }
    @When("^The user wants to delete the visit (.*)")
    public void deleteNonexistentVisit(String visit) {
        visitStepImpl.deleteNonexistentVisit(visit);
    }


    @And("^the visit shouldn't be in bd$")
    public void checkDeletedVet() {
        visitStepImpl.checkDeletedVisit();
    }

    @And("^the visit response should be null$")
    public void verifyInvalidGetById() {
        visitStepImpl.verifyInvalidGetById();
    }

    @When("^the user requests the visit (.*) by id$")
    public void getVisitById(String visit) {
        visitStepImpl.getVisitById();
        visits.add(visit);
    }

    @And("^verify the get visit response with DB$")
    public void checkVisitWithDb() {
        visitStepImpl.checkGetByIdVisit();
    }

    @When("^the user wants the visit (.*) by id$")
    public void getInvalidVisitById(String visit) {
        visitStepImpl.getVisitById(visit);
    }


    @After("@DeleteVisitData")
    public void deleteVets() {
        DatabaseStepImpl databaseStepImpl = new DatabaseStepImpl();
        databaseStepImpl.deleteFromTabe(tableName ,visits);
    }
}
