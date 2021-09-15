Feature: Test cases for pettype endpoint

  Test scripts for pettype endpoint.

  Background:
    Given The PetClinic application is up and running

  Scenario: Verify the list of pettypes
    When The user get the list with all pettypes
    Then A list of pettypes is returned

  Scenario: Verify that user can create a new pettype
    When The user creates pettype with name monkey
    Then The monkey pettype is in the response

  Scenario Outline: Verify that user can create a new pettype 2
    When The user creates pettype with name <name>
    Then The <name> pettype is in the response
    And The <name> pettype is created
    Examples:
      | name |
      | monkey |