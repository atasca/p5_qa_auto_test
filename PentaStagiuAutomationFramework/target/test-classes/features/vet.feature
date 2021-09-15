Feature: Vet

  Test scripts for vet .

  Background:
    Given The PetClinic application is up and running

    #/api/vets
    #getAllVets
  Scenario: Verify the list of vets for a user
    When The user get the list with all vets
    Then A list of vets is returned

    #/api/vets/{vetId}
    #getVet
  Scenario: Verify that a user get a vet with certain id
    When The user get the vet with id 1
    Then A vet is returned

    #/api/vets/{vetId}
    #updateVet
  Scenario: Verify that user can update a vet name
    When The user updates vet that have id 1 with new firstName Stef
      | lastName | specialitiesId |
      | Cotovanu | 2              |
    Then The vet firstName with id 1 is updated with Stef


  #/api/vets/{vetId}
  #deleteVet
  Scenario: Verify that a user delete a vet
    When The user get delete vet with id 1
    Then The vet with id 1 is deleted