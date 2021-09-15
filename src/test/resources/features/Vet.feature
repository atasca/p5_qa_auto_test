Feature: Test cases for vet endpoint

  Background:
    Given The PetClinic application is up and running

  @SmokeTest
    @RegresionTest
    @DBDeleteData
  Scenario Outline: Verify get all vets
    Given the vets <vets> are deleted from <DB>
    And the vets <vets> are added to <DB>
    When the user requests all vets
    Then the status code is <statusCode>
    And verify the get all vets response with DB
    Examples:
      | vets              | statusCode | DB   |
      | MarkTest,DaveTest | 200        | vets |

  @SmokeTest
    @RegresionTest
    @PostEndpoint @DeleteVetData
  Scenario Outline: Verify create vet successfully
    When the user creates new vet <vets> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of vet with DB

    Examples:
      | vets                         | contentType | statusCode | data    |
      | firstNameTest1,lastNameTest1 | json        | 201        | vetType |


  @RegresionTest
    @PostEndpoint @DeleteVetData
  Scenario Outline: Verify create vet invalid
    When the user creates new vet <vets> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of vet with DB

    Examples:
      | vets                         | contentType | statusCode | data               |
      | firstNameTest2,lastNameTest2 | txt         | 400        | invalidContentType |
      | invalidVet1,invalidVet2      | json        | 400        | invalidBody        |

  @SmokeTest
    @RegresionTest
    @UpdateEndpoint @DeleteVetData @DBDeleteData
  Scenario Outline: Verify update vet successfully
    Given the vets <vet> are added to <DB>
    And obtain id for vets <vet> from db
    When the user updates vet with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of vet with DB

    Examples:
      | vet         | data    | contentType | statusCode | newName       | DB   |
      | vetPutTest1 | vetType | json        | 204        | vetPutSuccess | vets |


  @RegresionTest
    @UpdateEndpoint @DeleteVetData @DBDeleteData
  Scenario Outline: Verify update vet invalid
    Given the vets <vet> are added to <DB>
    And obtain id for vets <vet> from db
    When the user updates vet with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response of vet with DB

    Examples:
      | vet         | data               | contentType | statusCode | newName       | DB   |
      | vetPutTest2 | invalidContentType | txt         | 400        | vetPutInvalid | vets |
      | nullvet     | invalidBody        | json        | 400        | vetPutNull    | vets |

  @SmokeTest
    @RegresionTest
    @DelPath
  Scenario Outline: Verify delete vet
    Given the vets <vets> are added to <DB>
    And obtain id for vets <vets> from db
    When The user requests to delete the vet
    Then the status code is <statusCode>
    And the vet shouldn't be in bd
    Examples:
      | vets          | statusCode | DB   |
      | deleteVetTest | 204        | vets |

  @RegresionTest
    @DelPath
  Scenario Outline: Verify delete for nonexistent vet
    When The user wants to delete the vet <vets>
    Then the status code is <statusCode>
    And the vet response should be null
    Examples:
      | vets               | statusCode |
      | nonExistentVetTest | 404        |

  @SmokeTest
    @RegresionTest
    @GetByIdPath @DBDeleteData
  Scenario Outline: Verify get vets by id
    Given the vets <vets> are deleted from <DB>
    And the vets <vets> are added to <DB>
    And obtain id for vets <vets> from db
    When the user requests the vet <vets> by id
    Then the status code is <statusCode>
    And verify the get vet response with DB
    Examples:
      | vets        | statusCode | DB   |
      | getByIdTest | 200        | vets |

  @RegresionTest
  Scenario Outline: Verify get by id with nonexistent vet
    When the user wants the vet <vets> by id
    Then the status code is <statusCode>
    And the vet response should be null
    Examples:
      | vets           | statusCode |
      | invalidGetById | 404        |



#  facem request post cu body invalid
#  verficam status code (400)
#  verficam response (in cazul in care nu avem body)

  #What to test

#  GET ALL vets:
#  - status code 200
#  - ce ne returneaza sa fie in db
#
#  stergem in caz ca exista 2 vets
#  adaugam 2 vets
#  facem request get all
#  verificam status code
#  verificam datale din request cu cele din db
#
#
#  Create vets
#  - testam inserarea in db
#  - status code
#  - respose -> get id
#  -- testam request body invalid(incomplet/inexistend)
#  -- body cu content type gresit
#
#  facem requst post
#  verficam status code
#  verficam response
#  verificam db cu id din response
#
#  facem request post cu body invalid
#  verficam status code (400)
#  verficam response

#  Get vet by id:
#    - status code 200
#    - ce ne returneaza sa fie in db
#
#  stergem in caz ca exista 2 vets
#  adaugam 2 vets
#  facem request get by id
#  verificam status code
#  verificam datale din request cu cele din db
#
#  Delete vet :
#    - status code 204
#    - verificare nu mai exista in db
#
#  adaugam in db un vet si salvam id
#  facem request la delete cu id-ul primit mai sus
#  verificam status code
#  verificam daca s-a sters in bd
#
#  Update vet
#    - check status
#    - verificare cu db
#  -- testam diferite id-uri (acelasi, diferit dar existent, inexistent)
#  -- testam request body invalid(incomplet/inexistend)
#  -- body cu content type gresit
#
#  adaugarea unui vet in db
#  facem req la put cu noile detalii
#  verificam status code
#  verificam update in db
#  stergem vet creat din db





