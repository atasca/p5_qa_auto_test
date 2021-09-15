Feature: Test cases for pet type endpoint

  Background:
    Given The PetClinic application is up and running
  @SmokeTest
    @RegresionTest
  @DBDeleteData
  Scenario Outline: Verify get all pet types
    Given the pet <types> are deleted from <DB>
    And the pet <types> are added to <DB>
    When the user requests all pet types
    Then the status code is <statusCode>
    And verify the get all pet types response with DB
    Examples:
      | types                | statusCode | DB    |
      | automation,developer | 200        | types |

  @SmokeTest
    @RegresionTest
  @PostPath @DeleteData
  Scenario Outline: Verify create pet type successfully
    When the user creates new pet <type> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the pet type response with DB

    Examples:
      | type          | contentType | statusCode | data               |
      | typePostTest1 | json        | 201        | typeType           |


  @RegresionTest
    @PostPath @DeleteData
  Scenario Outline: Verify create pet type invalid
    When the user creates new pet <type> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the pet type response with DB

    Examples:
      | type          | contentType | statusCode | data               |
      | typePostTest2 | txt         | 400        | invalidContentType |
      | invalidType   | json        | 400        | invalidBody        |


  @SmokeTest
    @RegresionTest
  @PutPath @DeleteData @DBDeleteData
  Scenario Outline: Verify update pet type succesfully
    Given the pet <types> are added to <DB>
    And obtain id for pet <types> from db
    When the user updates pet type with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the pet type response with DB

    Examples:
      | types        | data               | contentType | statusCode | newName        | DB    |
      | typePutTest1 | typeType           | json        | 204        | typePutSuccess | types |


  @RegresionTest
    @PutPath @DeleteData @DBDeleteData
  Scenario Outline: Verify update pet type invalid
    Given the pet <types> are added to <DB>
    And obtain id for pet <types> from db
    When the user updates pet type with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the pet type response with DB

    Examples:
      | types        | data               | contentType | statusCode | newName        | DB    |
      | typePutTest2 | invalidContentType | txt         | 400        | typePutInvalid | types |
      | nullType     | invalidBody        | json        | 400        | typePutNull    | types |

  @SmokeTest
    @RegresionTest
  @DelPath
  Scenario Outline: Verify delete pet type
    Given the pet <types> are added to <DB>
    And obtain id for pet <types> from db
    When The user requests to delete the pet types
    Then the status code is <statusCode>
    And the pet type shouldn't be in bd
    Examples:
      | types          | statusCode | DB    |
      | deleteTypeTest | 204        | types |

    @RegresionTest
  @DelPath
  Scenario Outline: Verify delete for nonexistent pet type
    When The user wants to delete the pet <type>
    Then the status code is <statusCode>
    And the pet type response should be null
    Examples:
      | type                | statusCode |
      | nonExistentTypeTest | 404        |

  @SmokeTest
    @RegresionTest
  @GetByIdPath @DeleteData
  Scenario Outline: Verify get pet type by id
    Given the pet <type> are deleted from <DB>
    And the pet <type> are added to <DB>
    And obtain id for pet <type> from db
    When the user requests the pet <type> by id
    Then the status code is <statusCode>
    And verify the get pet type response with DB
    Examples:
      | type        | statusCode | DB    |
      | getByIdTest | 200        | types |

    @RegresionTest
  Scenario Outline: Verify get by id with nonexistent pet type
    When the user wants the pet <type> by id
    Then the status code is <statusCode>
    And verify the get pet type response with DB
    Examples:
      | type           | statusCode |
      | invalidGetById | 404        |






