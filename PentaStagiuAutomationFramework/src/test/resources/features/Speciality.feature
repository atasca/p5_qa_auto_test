Feature: Test cases for speciality endpoint

  Scenario Outline: Verify get all specialities
    Given the specialities <specialities> are deleted from DB
    And the specialities <specialities> are added to DB
    When the user requests all specialities
    Then the status code is <statusCode>
    And verify the get all specialities response with DB
    Examples:
      | specialities         | statusCode |
      | automation,developer | 200        |

  Scenario Outline: Verify that user can create a new speciality
    When The user creates speciality with id <id> and name <name>
    Then The returned status code is corresponding
    And Verify response
    Examples:
      | id | name
      | [;  | vis'/d
