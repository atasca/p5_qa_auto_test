Feature: Visit

  Test scripts for visit .

  Background:
    Given The PetClinic application is up and running

  #/api/visits
  #getAllVisits
  #!!!bugs - am modificat in api din id in visits.id
  # in clasa JdbcVisitRepositoryImpl linia 125
@Regression
  Scenario: Verify the list of visits for a user
    When The user get the list with all visits
    Then A list of visits is returned

  #/api/visits
  #addVisit
  @Smoke
  Scenario: Verify that user can create a new visit
    When The user creates a new visit
      | date       | description | petId | petBirthDate | petOwnerId | typeId |
      | 2021/09/15 | test2       | 2     | 2020/09/15   | 2          | 2      |
    Then The test2 visit is created

  #api/visits/{visitId}
  #getVisit
  @Smoke
  Scenario: Verify that a user get a visit with certain id
    When The user get the visit with id 2
    Then A visit is returned

  #/api/visits/{visitId}
  #updateVisit
  @Smoke
  Scenario: Verify that user can update a visit description
    When The user updates visit that have id 100 with new description test3
      | date       | petId | petBirthDate | petOwnerId | typeId |
      | 2021/09/15 | 2     | 2020/09/15   | 2          | 2      |
    Then The visit description with id 105 is updated with test3

  #/api/visits/{visitId}
  #deleteVisit
  @Smoke
  Scenario: Verify that a user delete a visit
    When The user get delete visit with id 100
    Then The visit with id 100 is deleted