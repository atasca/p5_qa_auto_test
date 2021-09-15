Feature: Test cases for visits endpoint

  Background:
    Given The PetClinic application is up and running

  @SmokeTest
    @RegresionTest
    @DBDeleteData
  Scenario Outline: Verify get all pets
    Given the visit <visit> are deleted from <DB>
    And the visit <visit> are added to <DB>
    When the user requests all visits
    Then the status code is <statusCode>
    Examples:
      | visit                | statusCode | DB     |
      | automation,developer | 200        | visits |

  @SmokeTest
    @RegresionTest
    @PostEndpoint @DeleteVisitData
  Scenario Outline: Verify create vet successfully
    When the user creates new visit <visit> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of visit with DB

    Examples:
      | visit          | contentType | statusCode | data      |
      | firstNameTest1 | json        | 201        | visitType |

  @RegresionTest
    @PostEndpoint @DeleteVisitData
  Scenario Outline: Verify create vet invalid
    When the user creates new visit <visit> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of visit with DB

    Examples:
      | visit            | contentType | statusCode | data               |
      | descriptionTest2 | txt         | 400        | invalidContentType |
      | invalidVet1      | json        | 400        | invalidBody        |

  @SmokeTest
    @RegresionTest
    @UpdateEndpoint @DeleteVisitData @DBDeleteData
  Scenario Outline: Verify update visit successfully
    Given the visit <visit> are added to <DB>
    And obtain id for visit <visit> from db
    When the user updates visit with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of visit with DB

    Examples:
      | visit              | data      | contentType | statusCode | newName         | DB     |
      | descriptionTestPut | visitType | json        | 204        | visitPutSuccess | visits |

  @RegresionTest
    @UpdateEndpoint @DeleteVisitData @DBDeleteData
  Scenario Outline: Verify update visit invalid
    Given the visit <visit> are added to <DB>
    And obtain id for visit <visit> from db
    When the user updates visit with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of visit with DB

    Examples:
      | visit            | data               | contentType | statusCode | newName         | DB     |
      | descriptionTest2 | invalidContentType | txt         | 400        | visitPutInvalid | visits |
      | nullVisit        | invalidBody        | json        | 400        | visitPutNull    | visits |

  @SmokeTest
    @RegresionTest
    @DelPath
  Scenario Outline: Verify delete visit
    Given the visit <visit> are added to <DB>
    And obtain id for visit <visit> from db
    When The user requests to delete the visit
    Then the status code is <statusCode>
    And the visit shouldn't be in bd
    Examples:
      | visit           | statusCode | DB     |
      | deleteVisitTest | 204        | visits |

  @RegresionTest
    @DelPath
  Scenario Outline: Verify delete for nonexistent visit
    When The user wants to delete the visit <visit>
    Then the status code is <statusCode>
    And the visit response should be null
    Examples:
      | visit                | statusCode |
      | nonExistentVisitTest | 404        |

  @SmokeTest
    @RegresionTest
    @GetByIdPath @DBDeleteData
  Scenario Outline: Verify get visit by id
    Given the visit <visit> are deleted from <DB>
    And the visit <visit> are added to <DB>
    And obtain id for visit <visit> from db
    When the user requests the visit <visit> by id
    Then the status code is <statusCode>
    And verify the get visit response with DB
    Examples:
      | visit       | statusCode | DB     |
      | getByIdTest | 200        | visits |

  @RegresionTest
  Scenario Outline: Verify get by id with nonexistent visit
    When the user wants the visit <visit> by id
    Then the status code is <statusCode>
    And the visit response should be null
    Examples:
      | visit          | statusCode |
      | invalidGetById | 404        |