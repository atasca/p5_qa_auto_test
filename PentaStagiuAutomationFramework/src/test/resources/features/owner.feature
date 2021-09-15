Feature: Test cases for owner endpoint

  Test scripts for owner endpoint.

  Background:
    Given The PetClinic application is up and running
#!!!bugs -owner id dynamic

# /api/owners
# getOwners
  @Smoke
  Scenario: Verify the list of owners for a user
    When The user get the list with all owners
    Then A list of owners is returned

#/api/owners
# addOwner
  @Smoke
  Scenario: Verify that user can create a new owner
    When The user creates owner with firstName Cristina and lastName Pichiu
    Then The Cristina Pichiu owner is in the response

  #/api/owners
  # addOwner
  #/api/owners/*/lastname/{lastName}
  #getOwnersList
  @Smoke
  Scenario Outline: Verify that user can create a new owner 2
    When The user creates owner with firstName <firstName> and lastName <lastName>
    Then The <firstName> <lastName> owner is in the response
    And The <lastName> owner is created

    Examples:
      | firstName | lastName |
      | Andreea   | Popescu  |

  #/api/owners
  # addOwner
  @Smoke
  Scenario Outline: Verify that user can create a new owner - second example
    When The user creates a new owner with firstName <firstName> and lastName <lastName> second example
    Then The new <lastName> owner is created - second example

    Examples:

      | firstName | lastName |
      | Simona    | Pavel    |


  #/api/owners
  # addOwner
  @Regression
  Scenario: Verify that user can create a new owner - 3
    When The user creates a new owner
      | address      | city | firstName | lastName | telephone  | petName |
      | test address | Iasi | Laura     | Popescu  | 0763123456 | Felix   |
    Then The Popescu owner is created

    #/api/owners/{ownerId}
    #updateOwner
  @Smoke
  Scenario: Verify that user can update a owner name
    When The user updates owner that have id 1 with new first name Stefania
      | lastName | address            | city    | telephone  | petsId | typeId |
      | Cotovanu | 110 W. Liberty St. | Madison | 6085551023 | 2      | 1      |
    Then The owner first name with id 1 is updated with Maria

 # /api/owners/{ownerId}
 # deleteOwner
  @Smoke
  Scenario Outline: Verify that a user delete a owner
    When The user delete owner with id <id>
    Then The user with id <id> is deleted
    Examples:
      | id  |
      | 100 |
      | 101 |