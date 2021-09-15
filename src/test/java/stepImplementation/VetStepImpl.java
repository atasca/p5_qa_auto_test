package stepImplementation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import models.Specialty.Specialty;
import models.Vet.Body;
import models.Vet.GetDBResponse;
import models.Vet.GetVetApiResponse;
import models.Vet.InvalidBody;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.groups.Tuple;
import org.junit.Assert;
import rest.Endpoints;
import util.World;

import java.util.*;

import static net.serenitybdd.rest.SerenityRest.rest;

public class VetStepImpl {

    private Response response;
    private DatabaseStepImpl databaseimpl = new DatabaseStepImpl();
    private Body vetBody = Body.builder().build();
    private InvalidBody invalidVet = InvalidBody.builder().build();
    private ContentType type;
    private String data;
    private String newFirstName;
    private String newLastName ;


    public void getVet() {
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.VETS);
        World.setResponse(response);
    }

    public void getVetById() {
        String query = "/" + World.getVetResponse().getId().toString();
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.VETS + query);
        World.setResponse(response);
    }

    public void getVetById(String vet) {
        if (Objects.equals(vet, "invalidGetById")) {
            int id = databaseimpl.getMaxId("vets") + 1;
            String query = "/" + String.valueOf(id);
            response = rest()
                    .baseUri(Endpoints.BASE_URL)
                    .contentType(ContentType.JSON)
                    .get(Endpoints.VETS + query);
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

    public void checkResponse() {
        List<GetVetApiResponse> dbResponseList = databaseimpl.getAllVets();

        List<GetVetApiResponse> apiVetResponseList = Arrays.asList(World.getResponse().as(GetVetApiResponse[].class));

        Assert.assertEquals("Size is different, expected %s but was %s",apiVetResponseList.size(),dbResponseList.size());
        SoftAssertions softAssert = new SoftAssertions();
        apiVetResponseList.forEach(element -> softAssert.assertThat(element).as("Element not found").isIn(dbResponseList));

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

    public void addVet(String vet, String contentType, String data) {
        setContentType(contentType);
        List<Specialty> specialties = new ArrayList<>();
        List<String> vetNames = Arrays.asList(vet.split(","));
        vetBody.setFirstName(vetNames.get(0));
        vetBody.setLastName(vetNames.get(1));
        Specialty specialty = new Specialty();
        specialty.setId(1);
        specialty.setName("radiology");


        Object restBody;
        switch (data) {
            case "vetType":
                this.data = "vetType";
                specialties.add(specialty);
                vetBody.setSpecialties(specialties);
                restBody = vetBody;
                break;
            case "invalidContentType":
                this.data = "invalidContentType";
                restBody = vetBody.toString();
                break;
            case "invalidBody":
                this.data = "invalidBody";
                specialty.setName(null);
                specialties.add(specialty);
                vetBody.setSpecialties(specialties);
                restBody = vetBody;
                break;
            default:
                throw new UnsupportedOperationException();
        }

        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(this.type)
                .body(restBody)
                .post(Endpoints.VETS).then().log().all().and().extract().response();
        World.setResponse(response);

    }

    public void updateVet(String newName, String contentType, String data) {

        GetDBResponse dbVet;

        setContentType(contentType);
        dbVet = World.getVetResponse();
        vetBody.setFirstName(newName);
        vetBody.setLastName(newName);
        this.newFirstName = newName;
        this.newLastName = newName;

        Object restBody;
        switch (data) {
            case "vetType":
                this.data = "vetType";
                restBody = vetBody;
                break;
            case "invalidContentType":
                this.data = "invalidContentType";
                restBody = vetBody.toString();
                break;
            case "invalidBody":
                this.data = "invalidBody";
                invalidVet.setNam(newName);
                restBody = invalidVet;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(this.type)
                .body(restBody)
                .put(Endpoints.VETS + "/" + dbVet.getId().toString()).then().log().all().and().extract().response();
        World.setResponse(response);
    }

    public void deleteVet() {
        String queryId = "/" + World.getVetResponse().getId().toString();
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .delete(Endpoints.VETS + queryId);
        World.setResponse(response);
    }

    public void deleteNonexistentVet(String vet) {
        if (Objects.equals(vet, "nonExistentVetTest")) {
            int id = databaseimpl.getMaxId("vets") + 1;
            String queryId = "/" + String.valueOf(id);
            response = rest()
                    .baseUri(Endpoints.BASE_URL)
                    .contentType(ContentType.JSON)
                    .delete(Endpoints.VETS + queryId);
            World.setResponse(response);
        } else {
            throw new UnsupportedOperationException();

        }
    }

    public void checkDeletedVet() {
        SoftAssertions softAssert = new SoftAssertions();

        GetDBResponse nullVet = new GetDBResponse();
        GetDBResponse dbVet = databaseimpl.getVetById(World.getVetResponse().getId());

        softAssert.assertThat(dbVet)
                .isEqualTo(nullVet);
        softAssert.assertAll();
    }

    public void checkVet() {
        SoftAssertions softAssert = new SoftAssertions();
        Response apiResponse = World.getResponse();

        switch (this.data) {
            case "vetType":
                if (apiResponse.statusCode() == 200) {
                    GetVetApiResponse apiVet = apiResponse.as(GetVetApiResponse.class);
                    Integer id = apiVet.getId();
                    GetDBResponse dbVet = databaseimpl.getVetById(id);

                    softAssert.assertThat(apiVet)
                            .isEqualTo(dbVet);
                } else if (apiResponse.statusCode() == 204) {
                    GetDBResponse dbVet = databaseimpl.getVetById(World.getVetResponse().getId());
                    softAssert.assertThat(Tuple.tuple(dbVet.getFirstName(), dbVet.getLastName()))
                            .isEqualTo(Tuple.tuple(this.newFirstName,this.newLastName));
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

    public void checkGetByIdVet() {
        SoftAssertions softAssert = new SoftAssertions();

        GetDBResponse apiVet = World.getResponse().as(GetDBResponse.class);
        GetDBResponse dbVet = databaseimpl.getVetById(apiVet.getId());

        softAssert.assertThat(apiVet)
                .isEqualTo(dbVet);
        softAssert.assertAll();
    }

}
