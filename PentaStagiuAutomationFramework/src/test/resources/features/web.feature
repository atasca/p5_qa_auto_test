Feature: Test cases for web endpoint

  Test scripts for web endpoint.

  Background:
    Given The PetClinic application is up and running

  Scenario: Verify the list of links for a user
    When The user get the list with all the links
    Then A list of links is returned