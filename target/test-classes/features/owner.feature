Feature: Test cases for owner endpoint

  Test scripts for owner endpoint.

  Background:
    Given The PetClinic application is up and running

  @SmokeTest
    @RegresionTest
    @DBDeleteData
  Scenario Outline: Verify get all owners
    Given the owners <owners> are deleted from <DB>
    And the owners <owners> are added to <DB>
    When the user requests all owners
    Then the status code is <statusCode>
    And A list of owners is returned
    Examples:
      | owners            | statusCode | DB     |
      | MarkTest,DaveTest | 200        | owners |

  @SmokeTest
    @RegresionTest
    @PostEndpoint @DeleteOwnerData
  Scenario Outline: Verify create owners successfully
    When the user creates new owners <owners> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of owners with DB

    Examples:
      | owners                       | contentType | statusCode | data       |
      | firstNameTest1,lastNameTest1 | json        | 201        | ownersType |


  @RegresionTest
    @PostEndpoint @DeleteOwnerData
  Scenario Outline: Verify create owners invalid
    When the user creates new owners <owners> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of owners with DB

    Examples:
      | owners                       | contentType | statusCode | data               |
      | firstNameTest2,lastNameTest2 | txt         | 400        | invalidContentType |
      | invalidVet1,invalidVet2      | json        | 400        | invalidBody        |

  @SmokeTest
    @RegresionTest
    @UpdateEndpoint @DeleteOwnerData @DBDeleteData
  Scenario Outline: Verify update owners successfully
    Given the owners <owners> are added to <DB>
    And obtain id for owners <owners> from db
    When the user updates owners with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of owners with DB

    Examples:
      | owners         | data    | contentType | statusCode | newName          | DB     |
      | ownersPutTest1 | ownersType | json        | 204        | ownersPutSuccess | owners |


  @RegresionTest
    @UpdateEndpoint @DeleteOwnerData @DBDeleteData
  Scenario Outline: Verify update vet invalid
    Given the owners <owners> are added to <DB>
    And obtain id for owners <owners> from db
    When the user updates owners with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of owners with DB

    Examples:
      | owners         | data               | contentType | statusCode | newName          | DB     |
      | ownersPutTest2 | invalidContentType | txt         | 400        | ownersPutInvalid | owners |
      | nullOwners     | invalidBody        | json        | 400        | ownersPutNull    | owners |


  @SmokeTest
    @RegresionTest
    @DelPath
  Scenario Outline: Verify delete owners
    Given the owners <owners> are added to <DB>
    And obtain id for owners <owners> from db
    When The user requests to delete the owners
    Then the status code is <statusCode>
    And the owners shouldn't be in bd
    Examples:
      | owners          | statusCode | DB     |
      | deleteOwnerTest | 204        | owners |

  @RegresionTest
    @DelPath
  Scenario Outline: Verify delete for nonexistent owners
    When The user wants to delete the owners <owners>
    Then the status code is <statusCode>
    And the owners response should be null
    Examples:
      | owners               | statusCode |
      | nonExistentOwnerTest | 404        |

  @SmokeTest
    @RegresionTest
    @GetByIdPath @DeleteOwnerData
  Scenario Outline: Verify get owners by id
    Given the owners <owners> are deleted from <DB>
    And the owners <owners> are added to <DB>
    And obtain id for owners <owners> from db
    When the user requests the owners <owners> by id
    Then the status code is <statusCode>
    And verify the get owners response with DB
    Examples:
      | owners      | statusCode | DB     |
      | getByIdTest | 200        | owners |

  @RegresionTest
  Scenario Outline: Verify get by id with nonexistent owners
    When the user wants the owners <owners> by id
    Then the status code is <statusCode>
    And the owners response should be null
    Examples:
      | owners         | statusCode |
      | invalidGetById | 404        |


