Feature: Test cases for pet endpoint

  Test scripts for pet endpoint.

  Background:
    Given The PetClinic application is up and running

  Scenario: Verify the list of pets for a user
    When The user get the list with all pets
    Then A list of pets is returned

  Scenario: Verify that user can create a new pet
    When The user creates pet with name Rosi
    Then The Rosi pet is in the response

  Scenario Outline: Verify that user can create a new pet 2
    When The user creates pet with name <name>
    Then The <name> pet is in the response
    And The <name> pet is created
    Examples:
      | name |
      | Roz |