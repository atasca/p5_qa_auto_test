package stepImplementation;

import groovy.lang.Tuple;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Pet.*;
import models.Visit.PetVisit;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import rest.Endpoints;
import util.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static net.serenitybdd.rest.SerenityRest.rest;

public class PetStepImpl {

    private Response response;
    private DatabaseStepImpl databaseimpl = new DatabaseStepImpl();
    private Body petBody = Body.builder().build();
    private InvalidBody invalidPet = InvalidBody.builder().build();
    private ContentType type;
    private String data;
    private String newFirstName;
    private String newLastName ;


    public void getPet() {
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.PETS);
        World.setResponse(response);
    }

    public void checkResponse() {
        List<ApiResponse> dbResponseList = databaseimpl.getAllPets();

        List<ApiResponse> apiPetResponseList = Arrays.asList(World.getResponse().as(ApiResponse[].class));

        Assert.assertEquals("Size is different, expected %s but was %s",dbResponseList.size(),apiPetResponseList.size());
        SoftAssertions softAssert = new SoftAssertions();
        apiPetResponseList.forEach(element -> softAssert.assertThat(element).as("Element not found").isIn(dbResponseList));

    }

    public void getPetById() {
        String query = "/" + World.getPetResponse().getId();
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.PETS + query);
        World.setResponse(response);
    }

    public void getPetById(String pet) {
        if (Objects.equals(pet, "invalidGetById")) {
            int id = databaseimpl.getMaxId("pets") + 1;
            String query = "/" + String.valueOf(id);
            response = rest()
                    .baseUri(Endpoints.BASE_URL)
                    .contentType(ContentType.JSON)
                    .get(Endpoints.PETS + query);
            World.setResponse(response);
        } else {
            throw new UnsupportedOperationException();

        }
    }

    public void verifyInvalidGetById() {
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(World.getResponse().asString())
                .isEmpty();
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

    public void addPet(String pet, String contentType, String data) {
        setContentType(contentType);
        List<String> petNames = Arrays.asList(pet.split(","));
        petBody.setName(petNames.get(0));
        petBody.setBirthDate("2000/09/07");
        petBody.setOwnerId(1);
        petBody.setTypeId(1);

        Object restBody;
        switch (data) {
            case "petType":
                this.data = "petType";
                restBody = petBody;
                break;
            case "invalidContentType":
                this.data = "invalidContentType";
                restBody = petBody.toString();
                break;
            case "invalidBody":
                this.data = "invalidBody";
                invalidPet.setOwnerId(null);
                restBody = invalidPet;
                break;
            default:
                throw new UnsupportedOperationException();
        }

        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(this.type)
                .body(restBody)
                .post(Endpoints.PETS).then().log().all().and().extract().response();
        World.setResponse(response);

    }

    public void updatePet(String newName, String contentType, String data) {

        Pet dbPet;

        setContentType(contentType);
        dbPet = World.getPetResponse();
        petBody.setName(newName);
        this.newFirstName=newName;
        petBody.setBirthDate("2000/09/07");
        petBody.setOwnerId(1);
        petBody.setTypeId(1);


        Object restBody;
        switch (data) {
            case "petType":
                this.data = "petType";
                restBody = petBody;
                break;
            case "invalidContentType":
                this.data = "invalidContentType";
                restBody = petBody.toString();
                break;
            case "invalidBody":
                this.data = "invalidBody";
                invalidPet.setName(newName);
                invalidPet.setOwnerId(1);
                invalidPet.setTypeId(1);
                restBody = invalidPet;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(this.type)
                .body(restBody)
                .put(Endpoints.PETS + "/" + dbPet.getId()).then().log().all().and().extract().response();
        World.setResponse(response);
    }

    public void deletePet() {
        String queryId = "/" + World.getPetResponse().getId();
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .delete(Endpoints.PETS + queryId);
        World.setResponse(response);
    }

    public void deleteNonexistentPet(String pet) {
        if (Objects.equals(pet, "nonExistentPetTest")) {
            int id = databaseimpl.getMaxId("pets") + 1;
            String queryId = "/" + String.valueOf(id);
            response = rest()
                    .baseUri(Endpoints.BASE_URL)
                    .contentType(ContentType.JSON)
                    .delete(Endpoints.PETS + queryId);
            World.setResponse(response);
        } else {
            throw new UnsupportedOperationException();

        }
    }

    public void checkDeletedPet() {
        SoftAssertions softAssert = new SoftAssertions();

        Body nullPet = new Body();
        Body dbPet = databaseimpl.getPetById(World.getPetResponse().getId());

        softAssert.assertThat(dbPet)
                .isEqualTo(nullPet);
        softAssert.assertAll();
    }

    public void checkPet() {
        SoftAssertions softAssert = new SoftAssertions();
        Response apiResponse = World.getResponse();

        switch (this.data) {
            case "petType":
                if (apiResponse.statusCode() == 200) {
                    ApiResponse apiPet = apiResponse.as(ApiResponse.class);
                    Integer id = apiPet.getId();
                    Body dbPet = databaseimpl.getPetById(id);

                    softAssert.assertThat(apiPet)
                            .isEqualTo(dbPet);
                } else if (apiResponse.statusCode() == 204) {
                    Body dbPet = databaseimpl.getPetById(World.getPetResponse().getId());
                    softAssert.assertThat(dbPet)
                            .isEqualTo(petBody);
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

    public void checkGetByIdPet() {
        SoftAssertions softAssert = new SoftAssertions();

        Body apiPet = World.getResponse().as(Body.class);
        Body dbPet = databaseimpl.getPetById(apiPet.getId());

        softAssert.assertThat(apiPet)
                .isEqualTo(dbPet);
        softAssert.assertAll();
    }

}
