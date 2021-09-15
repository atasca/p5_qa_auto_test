Feature: Test cases for visit endpoint

  Test scripts for visit endpoint.

  Background:
    Given The PetClinic application is up and running

  Scenario: Verify the list of visits for a user
    When The user get the list with all visits
    Then A list of visits is returned

  Scenario Outline: Verify that user can create a new vet
    When The user creates visit with description <description>
    Then The <description> visit is in the response
    And The <descritpion> visit is created

    Examples:
      | description |
      | tratament  |