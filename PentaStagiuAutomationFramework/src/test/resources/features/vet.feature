
Feature: Test cases for vet endpoint

  Test scripts for vet endpoint.

  Background:
    Given The PetClinic application is up and running

  Scenario: Verify the list of vets for a user
    When The user get the list with all vets
    Then A list of vets is returned