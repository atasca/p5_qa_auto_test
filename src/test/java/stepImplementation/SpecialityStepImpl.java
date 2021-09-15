package stepImplementation;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Specialty.Specialty;
import org.assertj.core.api.SoftAssertions;
import rest.Endpoints;
import util.World;

import models.Specialty.*;

import java.util.List;
import java.util.Objects;

import static net.serenitybdd.rest.SerenityRest.rest;


public class SpecialityStepImpl {

    private Response response;
    private DatabaseStepImpl databaseimpl = new DatabaseStepImpl();
    private Body specialtyBody = Body.builder().build();
    private InvalidBody invalidSpecialty= InvalidBody.builder().build();
    private ContentType type;
    private String data;
    private String newName;


    public void getSpecialties() {
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.SPECIALTIES);
        World.setResponse(response);
    }

    public void getSpecialtyById() {
        String query = "/" + World.getSpecialtyResponse().getId().toString();
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.SPECIALTIES + query);
        World.setResponse(response);
    }

    public void getSpecialtyById(String specialty) {
        if (Objects.equals(specialty, "invalidGetById")) {
            int id = databaseimpl.getMaxId("specialties") +1;
            String query = "/" + String.valueOf(id) ;
            response = rest()
                    .baseUri(Endpoints.BASE_URL)
                    .contentType(ContentType.JSON)
                    .get(Endpoints.SPECIALTIES + query);
            World.setResponse(response);
        }else {
            throw new UnsupportedOperationException();

        }
    }

    public void verifyInvalidGetById(){
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(World.getResponse().asString())
                .isEmpty();
        softAssert.assertAll();
    }

    public void checkResponse() {
        List<Specialty> dbResponse = databaseimpl.getAllSpecialities();

        Specialty[] dbResponseArray = new Specialty[dbResponse.size()];
        dbResponseArray = dbResponse.toArray(dbResponseArray);

        Specialty[] getSpecialtyResponse = World.getResponse().as(Specialty[].class);
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(dbResponseArray)
                .as("responses are different")
                .isEqualTo(getSpecialtyResponse);
        softAssert.assertAll();

    }

    public void setContentType(String contentType) {
        if (Objects.equals(contentType, "json"))
            this.type = ContentType.JSON;
        else {
            if (Objects.equals(contentType, "txt"))
                this.type = ContentType.TEXT;
            else
                this.type = ContentType.JSON;
        }

    }

    public void addSpecialties(String specialty, String contentType, String data) {
        setContentType(contentType);
        specialtyBody.setName(specialty);

        Object restBody;
        switch (data) {
            case "specialtyType":
                this.data = "specialtyType";
                restBody = specialtyBody;
                break;
            case "invalidContentType":
                this.data = "invalidContentType";
                restBody = specialtyBody.toString();
                break;
            case "invalidBody":
                this.data = "invalidBody";
                invalidSpecialty.setNam(specialty);
                restBody = invalidSpecialty;
                break;
            default:
                throw new UnsupportedOperationException();
        }

        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(this.type)
                .body(restBody)
                .post(Endpoints.SPECIALTIES).then().log().all().and().extract().response();
        World.setResponse(response);

    }

    public void updateSpecialty(String newName, String contentType, String data) {

        Specialty dbSpecialty;

        setContentType(contentType);
        dbSpecialty = World.getSpecialtyResponse();
        specialtyBody.setName(newName);
        this.newName=newName;

        Object restBody;
        switch (data) {
            case "specialtyType":
                this.data = "specialtyType";
                restBody = specialtyBody;
                break;
            case "invalidContentType":
                this.data = "invalidContentType";
                restBody = specialtyBody.toString();
                break;
            case "invalidBody":
                this.data = "invalidBody";
                invalidSpecialty.setNam(newName);
                restBody = invalidSpecialty;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(this.type)
                .body(restBody)
                .put(Endpoints.SPECIALTIES+ "/"+ dbSpecialty.getId().toString()).then().log().all().and().extract().response();
        World.setResponse(response);
    }

    public void deleteSpecialty() {
        String queryId = "/" + World.getSpecialtyResponse().getId().toString();
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .delete(Endpoints.SPECIALTIES + queryId);
        World.setResponse(response);
    }

    public void deleteNonexistentSpecialty(String specialty){
        if(Objects.equals(specialty, "nonExistentSpecialtyTest")) {
            int id = databaseimpl.getMaxId("specialties") + 1;
            String queryId = "/" + String.valueOf(id);
            response = rest()
                    .baseUri(Endpoints.BASE_URL)
                    .contentType(ContentType.JSON)
                    .delete(Endpoints.SPECIALTIES + queryId);
            World.setResponse(response);
        }else {
            throw new UnsupportedOperationException();

        }
    }

    public void checkDeletedSpecialty() {
        SoftAssertions softAssert = new SoftAssertions();

        Specialty nullSpecialty = new Specialty();
        Specialty dbSpecialty = databaseimpl.getSpecialtyById(World.getSpecialtyResponse().getId());

        softAssert.assertThat(dbSpecialty)
                .isEqualTo(nullSpecialty);
        softAssert.assertAll();
    }

    public void checkSpecialty() {
        SoftAssertions softAssert = new SoftAssertions();
        Response apiResponse = World.getResponse();

        switch (this.data) {
            case "specialtyType":
                if (apiResponse.statusCode()==200) {
                    Specialty apiSpecialty = apiResponse.as(Specialty.class);
                    Integer id = apiSpecialty.getId();
                    Specialty dbSpecialty = databaseimpl.getSpecialtyById(id);

                    softAssert.assertThat(apiSpecialty)
                            .isEqualTo(dbSpecialty);
                }
                else if(apiResponse.statusCode()==204){
                   Specialty dbSpecialty = databaseimpl.getSpecialtyById(World.getSpecialtyResponse().getId());
                    softAssert.assertThat(dbSpecialty.getName())
                            .isEqualTo(this.newName);
                }
                break;
            case "invalidContentType":
            case "invalidBody":
                softAssert.assertThat(apiResponse.toString())
                        .contains("RestAssuredResponseImpl");
                break;
            default:
                throw new UnsupportedOperationException();

        }
        softAssert.assertAll();

    }

    public void checkGetByIdSpecialty(){
        SoftAssertions softAssert = new SoftAssertions();

        Specialty apiSpecialty = World.getSpecialtyResponse();
        Specialty dbSpecialty = databaseimpl.getSpecialtyById(apiSpecialty.getId());

        softAssert.assertThat(dbSpecialty)
                .isEqualTo(apiSpecialty);
        softAssert.assertAll();
    }


}
