Feature: Test cases for user endpoint

  Test scripts for user endpoint.

  Background:
    Given The PetClinic application is up and running

  Scenario Outline: Verify that a new user can be created
    When Create user with username <username>
    Then The <username> user is in the response
    And The <username> user is created

    Examples:
      | username|
      | Adri |