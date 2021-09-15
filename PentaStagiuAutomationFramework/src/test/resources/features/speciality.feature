Feature: Test cases for speciality endpoint

  Test scripts for speciality .

  Background:
    Given The PetClinic application is up and running


  #/api/specialties getAllSpecialtys
  #/api/specialties addSpecialty
  #/api/specialties/{specialtyId} deleteSpecialty
  @Smoke
  Scenario Outline: Verify get all specialities
    Given the specialities <specialities> are deleted from DB
    And the specialities <specialities> are added to DB
    When the user requests all specialities
    Then the status code is <statusCode>
    And verify the get all specialities response with DB

    Examples:
      | specialities         | statusCode |
      | automation,developer | 200        |


  #/api/specialties/{specialtyId} getSpecialty
  @Smoke
  Scenario: Verify that a user get a speciality with certain id
    When The user get the speciality with id 1
    Then A speciality is returned


  #/api/specialties/{specialtyId} updateSpecialty
  @Smoke
  Scenario Outline:Verify that user can update a speciality
    When The user updates speciality that have id <id> with new name <newName>
    Then The new name of the speciality with id <id> is updated with <newName>

    Examples:
      | id  | newName |
      | 168 | dev     |
      | 169 | tester  |


  #/api/specialties/{specialtyId} deleteSpecialty
  @Smoke
  Scenario Outline: Verify that a user delete a speciality
    When The user get delete speciality with id <id>
    Then The speciality with id <id> is deleted
    Examples:
      | id  |
      | 168 |
      | 169 |