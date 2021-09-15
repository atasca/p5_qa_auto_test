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

#  facem request post cu body invalid
#  verficam status code (400)
#  verficam response (in cazul in care nu avem body)

##Tema:
##  - finalizat scenariul pozitiv
  #(folositi structura step defs -> step impl
  # folositi World.getResponse World.setResponse pentru a prelua raspunsul intre stepi
  # folositi SoftAssert pentru a compara in ultimul step din testul pozitiv datele din obiectul response cu cele
  # din baza de date : e.g
#   SoftAssertions softassert = new SoftAssertions();
#      softassert.assertThat(10)
#          .as("number is different")
#          .isEqualTo(11);
#      softassert.assertAll();)
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


  Scenario Outline: Verify that user can create a new speciality
    When The user creates speciality with id <id> and name <name>
    Then The returned status code is corresponding
    And Verify response
    Examples:
      | id | name
      | [;  | vis'/d
