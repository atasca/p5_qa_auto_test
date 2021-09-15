Feature: Test cases for pet endpoint

  Background:
    Given The PetClinic application is up and running

  @SmokeTest
    @RegresionTest
    @DBDeleteData
  Scenario Outline: Verify get all pets
    Given the pets <pets> are deleted from <DB>
    And the pets <pets> are added to <DB>
    When the user requests all pets
    Then the status code is <statusCode>
    And verify the get all pets response with DB
    Examples:
      | pets                 | statusCode | DB   |
      | automation,developer | 200        | pets |

  @SmokeTest
    @RegresionTest
    @PostPath @DeleteData
  Scenario Outline: Verify create pet successdfully
    When the user creates new pets <pet> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the pet response with DB

    Examples:
      | pet          | contentType | statusCode | data    |
      | petPostTest1 | json        | 201        | petType |


  @RegresionTest
    @PostPath @DeleteData
  Scenario Outline: Verify create pet invalid
    When the user creates new pets <pet> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the pet response with DB

    Examples:
      | pet          | contentType | statusCode | data               |
      | petPostTest2 | txt         | 400        | invalidContentType |
      | invalidPet   | json        | 400        | invalidBody        |

  @SmokeTest
    @RegresionTest
    @PutPath @DeleteData @DBDeleteData
  Scenario Outline: Verify update pet successfully
    Given the pets <pet> are added to <DB>
    And obtain id for pets <pet> from db
    When the user updates pet with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the pet response with DB

    Examples:
      | pet         | data    | contentType | statusCode | newName       | DB   |
      | petPutTest1 | petType | json        | 204        | petPutSuccess | pets |

  @RegresionTest
    @PutPath @DeleteData @DBDeleteData
  Scenario Outline: Verify update pet invalid
    Given the pets <pet> are added to <DB>
    And obtain id for pets <pet> from db
    When the user updates pet with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the pet response with DB

    Examples:
      | pet         | data               | contentType | statusCode | newName       | DB   |
      | petPutTest2 | invalidContentType | txt         | 400        | petPutInvalid | pets |
      | nullPet     | invalidBody        | json        | 400        | petPutNull    | pets |

  @SmokeTest
    @RegresionTest
    @DelPath
  Scenario Outline: Verify delete pet
    Given the pets <pet> are added to <DB>
    And obtain id for pets <pet> from db
    When The user requests to delete the pet
    Then the status code is <statusCode>
    And the pet shouldn't be in bd
    Examples:
      | pet           | statusCode | DB    |
      | deletePetTest | 204        | types |

  @RegresionTest
    @DelPath
  Scenario Outline: Verify delete for nonexistent pet
    When The user wants to delete the pets <pet>
    Then the status code is <statusCode>
    And the pet response should be null
    Examples:
      | pet                | statusCode |
      | nonExistentPetTest | 404        |

  @SmokeTest
    @RegresionTest
    @GetByIdPath @DeleteData
  Scenario Outline: Verify get pet by id
    Given the pets <pet> are deleted from <DB>
    And the pets <pet> are added to <DB>
    And obtain id for pets <pet> from db
    When the user requests the pets <pet> by id
    Then the status code is <statusCode>
    And verify the get pet response with DB
    Examples:
      | pet         | statusCode | DB    |
      | getByIdTest | 200        | types |

  @RegresionTest
  Scenario Outline: Verify get by id with nonexistent pet
    When the user wants the pets <pet> by id
    Then the status code is <statusCode>
    And verify the get pet response with DB
    Examples:
      | pet            | statusCode |
      | invalidGetById | 404        |






