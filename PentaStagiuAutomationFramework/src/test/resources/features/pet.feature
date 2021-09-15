Feature: Pets

  Test scripts for pets .

  Background:
    Given The PetClinic application is up and running

  #/api/pets
  #getPets
  @Smoke
  Scenario: Verify the list of pet for a user
    When The user get the list with all pets
    Then A list of pets is returned

 #/api/pets
 #addPet
  @Smoke
  Scenario: Verify that user can create a new pet
    When The user creates a new pet
      | birthDate  | name | ownerId | typeId |
      | 2021/09/04 | Mara | 2       | 1      |
    Then The Mara pet is created

  #/api/pets/{petId}
  #getPet
  @Smoke
  Scenario: Verify that a user get a pet with certain id
    When The user get the pet with id 1
    Then A pet is returned

  #/api/pets/{petId}
  #updatePet
  @Smoke
  Scenario: Verify that user can update a pet name
    When The user updates pet that have id 1 with new name Bibi
      | birthDate  | ownerId | typeId |
      | 2021/09/04 | 3       | 2      |
    Then The pet name with id 3 is updated with Bibi

  #/api/pets/{petId}
  #deletePet
  @Smoke
  Scenario: Verify that a user delete a pet
    When The user get delete pet with id 100
    Then The pet with id 100 is deleted

  #/api/pets/pettypes
  #getPetTypes
  @Smoke
  Scenario: Verify the list of pet types for a user
    When The user get the list with all pets types
    Then A list of pets types is returned

  #/api/pettypes
  #addPetType
  # un user nu poate crea, putem crea doar daca acel id exista
  @Smoke
  Scenario: Verify that user can create a new pet type
    When The user creates a new pet type
      | petId | petName |
      | 5     | dog  |
    Then The dog pet type is created

  #/api/pettypes/{petTypeId}
  #getPetType
  @Regression
  Scenario: Verify that a user get a pet type with certain id
    When The user get the pet type with id 5
    Then A pet type is returned

  #/api/pettypes/{petTypeId}
  #updatePetType
  @Smoke
  Scenario Outline: Verify that user can update a pet type
    When The user updates pet type that have id <id> with new name <newName>
    Then The new name of the pet type with id <id> is updated with <newName>

    Examples:
      | id | newName |
      | 2  | quokka  |
      | 3  | parrot  |

  #/api/pettypes/{petTypeId}
  #deletePetType
  @Smoke
  Scenario Outline: Verify that a user delete a pet type
    When The user get delete pet type with id <id>
    Then The pet type with id <id> is deleted
    Examples:
      | id |
      | 2  |

