Feature: Test cases for speciality endpoint

  Background:
    Given The PetClinic application is up and running

  @SmokeTest
    @RegresionTest
    @DBDeleteData
  Scenario Outline: Verify get all specialities
    Given the specialities <specialities> are deleted from DB
    And the specialities <specialities> are added to <DB>
    When the user requests all specialities
    Then the status code is <statusCode>
    And verify the get all specialities response with DB
    Examples:
      | specialities         | statusCode | DB          |
      | automation,developer | 200        | specialties |

  @SmokeTest
    @RegresionTest
    @PostPath @DeleteData
  Scenario Outline: Verify create specialities successfully
    When the user creates new specialty <speciality> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response with DB

    Examples:
      | speciality         | contentType | statusCode | data          |
      | specialtyPostTest1 | json        | 201        | specialtyType |


  @RegresionTest
    @PostPath @DeleteData
  Scenario Outline: Verify create specialities invalid
    When the user creates new specialty <speciality> with <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response with DB

    Examples:
      | speciality         | contentType | statusCode | data               |
      | specialtyPostTest2 | txt         | 400        | invalidContentType |
      | invalidSpecialty   | json        | 400        | invalidBody        |

  @SmokeTest
    @RegresionTest
    @PutPath @DeleteData @DBDeleteData
  Scenario Outline: Verify update specialty successfully
    Given the specialities <specialty> are added to <DB>
    And obtain id for specialty <specialty> from db
    When the user updates specialty with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response with DB

    Examples:
      | specialty         | data          | contentType | statusCode | newName             | DB          |
      | specialtyPutTest1 | specialtyType | json        | 204        | specialtyPutSuccess | specialties |


  @RegresionTest
    @PutPath @DeleteData @DBDeleteData
  Scenario Outline: Verify update specialty invalid
    Given the specialities <specialty> are added to <DB>
    And obtain id for specialty <specialty> from db
    When the user updates specialty with <newName> and <contentType> having <data>
    Then the status code is <statusCode>
    And verify the response with DB

    Examples:
      | specialty         | data               | contentType | statusCode | newName             | DB          |
      | specialtyPutTest2 | invalidContentType | txt         | 400        | specialtyPutInvalid | specialties |
      | nullSpecialty     | invalidBody        | json        | 400        | specialtyPutNull    | specialties |

  @SmokeTest
    @RegresionTest
    @DelPath
  Scenario Outline: Verify delete specialty
    Given the specialities <speciality> are added to <DB>
    And obtain id for specialty <speciality> from db
    When The user requests to delete the specialty
    Then the status code is <statusCode>
    And the specialty shouldn't be in bd
    Examples:
      | speciality          | statusCode | DB          |
      | deleteSpecialtyTest | 204        | specialties |

  @RegresionTest
    @DelPath
  Scenario Outline: Verify delete for nonexistent specialty
    When The user wants to delete the specialty <speciality>
    Then the status code is <statusCode>
    And the response should be null
    Examples:
      | speciality               | statusCode |
      | nonExistentSpecialtyTest | 404        |

  @SmokeTest
    @RegresionTest
    @GetByIdPath @DeleteData
  Scenario Outline: Verify get speciality by id
    Given the specialities <speciality> are deleted from DB
    And the specialities <speciality> are added to <DB>
    And obtain id for specialty <speciality> from db
    When the user requests the specialty <speciality> by id
    Then the status code is <statusCode>
    And verify the get speciality response with DB
    Examples:
      | speciality  | statusCode | DB          |
      | getByIdTest | 200        | specialties |

  @RegresionTest
  Scenario Outline: Verify get by id with nonexistent specialty
    When the user wants the speciality <speciality> by id
    Then the status code is <statusCode>
    And the response should be null
    Examples:
      | speciality     | statusCode |
      | invalidGetById | 404        |



#  facem request post cu body invalid
#  verficam status code (400)
#  verficam response (in cazul in care nu avem body)

##Tema:
##  - finalizat scenariul pozitiv
  #(folositi structura step defs -> step impl
  # folositi World.getResponse World.setResponse pentru a prelua raspunsul intre stepi
  # folositi SoftAssert pentru a compara in ultimul step din testul pozitiv datele din obiectul response cu cele
  # din baza de date : e.g
  # SoftAssertions softassert = new SoftAssertions();
  #    softassert.assertThat(10)
  #        .as("number is different")
  #        .isEqualTo(11);
  #    softassert.assertAll();)
##  - finalizat scenariul negativ pentru post



  #What to test

#  GET ALL SPECIALITIES:
#  - status code 200
#  - ce ne returneaza sa fie in db
#
#  stergem in caz ca exista 2 specialitati
#  adaugam 2 specialitati
#  facem request get all
#  verificam status code
#  verificam datale din request cu cele din db
#
#
#  Create Speciality
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

#  Get specialty by id:
#    - status code 200
#    - ce ne returneaza sa fie in db
#
#  stergem in caz ca exista 2 specialitati
#  adaugam 2 specialitati
#  facem request get by id
#  verificam status code
#  verificam datale din request cu cele din db
#
#  Delete Specialty :
#    - status code 204
#    - verificare nu mai exista in db
#
#  adaugam in db o specialitate si salvam id
#  facem request la delete cu id-ul primit mai sus
#  verificam status code
#  verificam daca s-a sters in bd
#
#  Update specialty
#    - check status
#    - verificare cu db
#  -- testam diferite id-uri (acelasi, diferit dar existent, inexistent)
#  -- testam request body invalid(incomplet/inexistend)
#  -- body cu content type gresit
#
#  adaugarea unei specialitati in db
#  facem req la put cu noile detalii
#  verificam status code
#  verificam update in db
#  stergem specialitatea creata din db





