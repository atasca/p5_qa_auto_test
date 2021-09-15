package stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Value;
import stepImplementation.DatabaseStepImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatabaseStepDefinitions {

  ArrayList<String> names = new ArrayList<>();
  String table;

  @Steps
  DatabaseStepImpl databaseStepImpl= new DatabaseStepImpl();



  @Given("^the specialities (.*) are deleted from DB$")
  public void deleteSpecialities (String specialities) {

    databaseStepImpl.deleteSpecialities(Arrays.asList(specialities.split(",")));

  }

  @Given("^the vets (.*) are deleted from (.*)$")
  public void deleteVets (String vets, String tableName) {

    databaseStepImpl.deleteFromTabe(tableName, Arrays.asList(vets.split(",")));

  }

  @Given("^the owners (.*) are deleted from (.*)$")
  public void deleteOwners (String owners, String tableName) {

    databaseStepImpl.deleteFromTabe(tableName, Arrays.asList(owners.split(",")));

  }

  @Given("^the pet (.*) are deleted from (.*)$")
  public void deleteTypes (String types, String tableName) {

    databaseStepImpl.deleteFromTabe(tableName, Arrays.asList(types.split(",")));

  }

  @Given("^the pets (.*) are deleted from (.*)$")
  public void deletePets (String pets, String tableName) {

    databaseStepImpl.deleteFromTabe(tableName, Arrays.asList(pets.split(",")));

  }

  @Given("^the visit (.*) are deleted from (.*)$")
  public void deleteVisits (String visits, String tableName) {

    databaseStepImpl.deleteFromTabe(tableName, Arrays.asList(visits.split(",")));

  }

  @And("^the specialities (.*) are added to (.*)$")
  public void addSpecialties(String specialities, String tableName){
    databaseStepImpl.addSpecialties(Arrays.asList(specialities.split(",")));
    this.names.addAll(Arrays.asList(specialities.split(",")));
    this.table = tableName;
  }

  @And("^the vets (.*) are added to (.*)$")
  public void addVets(String vets, String tableName){
    List<String> vetNames = Arrays.asList(vets.split(","));
    databaseStepImpl.addVets(vetNames);
    names.addAll(vetNames);
    table = tableName;

  }

  @And("^the owners (.*) are added to (.*)$")
  public void addOwners(String owners, String tableName){
    List<String> ownerNames = Arrays.asList(owners.split(","));
    databaseStepImpl.addOwners(ownerNames);
    names.addAll(ownerNames);
    table = tableName;

  }

  @And("^the pet (.*) are added to (.*)$")
  public void addTypes(String pets, String tableName){
    List<String> typeName = Arrays.asList(pets.split(","));
    databaseStepImpl.addTypes(typeName);
    names.addAll(typeName);
    table = tableName;

  }

  @And("^the visit (.*) are added to (.*)$")
  public void addVisits(String visits, String tableName){
    List<String> typeName = Arrays.asList(visits.split(","));
    databaseStepImpl.addVisits(typeName);
    names.addAll(typeName);
    table = tableName;

  }

  @And("^the pets (.*) are added to (.*)$")
  public void addPets(String pets, String tableName){
    List<String> typeName = Arrays.asList(pets.split(","));
    databaseStepImpl.addPets(typeName);
    names.addAll(typeName);
    table = tableName;

  }

  @And("^obtain id for specialty (.*) from db$")
  public void getId(String name){
    databaseStepImpl.getSpecialtyIdByName(name);
    names.add(name);
  }

  @And("^obtain id for vets (.*) from db$")
  public void getVetId(String name){
    databaseStepImpl.getVetIdByLastName(name);
  }

  @And("^obtain id for owners (.*) from db$")
  public void getOwnerId(String name){
    databaseStepImpl.getOwnerIdByFirstName(name);
  }
  @And("^obtain id for pet (.*) from db$")
  public void getTypeId(String name){
    databaseStepImpl.getTypeIdByName(name);
    names.add(name);
  }

  @And("^obtain id for visit (.*) from db$")
  public void getVisitId(String description){
    databaseStepImpl.getVisitIdByDescription(description);
  }

  @And("^obtain id for pets (.*) from db$")
  public void getPetId(String name){
    databaseStepImpl.getPetIdByName(name);
    names.add(name);
  }

  @After("@DBDeleteData")
  public void deleteVets() {
    databaseStepImpl.deleteFromTabe(table, names);
  }

}
