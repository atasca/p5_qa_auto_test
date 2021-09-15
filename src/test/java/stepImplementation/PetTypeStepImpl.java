package stepImplementation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.PetType.Type;
import models.PetType.Body;
import models.PetType.InvalidBody;

import models.Specialty.Specialty;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import rest.Endpoints;
import util.World;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static net.serenitybdd.rest.SerenityRest.rest;

public class PetTypeStepImpl {

    private Response response;
    private DatabaseStepImpl databaseimpl = new DatabaseStepImpl();
    private Body typeBody = Body.builder().build();
    private InvalidBody invalidType = InvalidBody.builder().build();
    private ContentType type;
    private String data;
    private String newName;


    public void getTypes() {
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.TYPES);
        World.setResponse(response);
    }

    public void checkResponse() {
        List<Type> dbResponseList = databaseimpl.getAllTypes();

        List<Type> apiVetResponseList = Arrays.asList(World.getResponse().as(Type[].class));

        Assert.assertEquals("Size is different, expected %s but was %s", apiVetResponseList.size(), dbResponseList.size());
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

    public void addType(String type, String contentType, String data) {
        setContentType(contentType);
        typeBody.setName(type);

        Object restBody;
        switch (data) {
            case "typeType":
                this.data = "typeType";
                restBody = typeBody;
                break;
            case "invalidContentType":
                this.data = "invalidContentType";
                restBody = typeBody.toString();
                break;
            case "invalidBody":
                this.data = "invalidBody";
                invalidType.setNam(type);
                restBody = invalidType;
                break;
            default:
                throw new UnsupportedOperationException();
        }

        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(this.type)
                .body(restBody)
                .post(Endpoints.TYPES).then().log().all().and().extract().response();
        World.setResponse(response);

    }

    public void checkType() {
        SoftAssertions softAssert = new SoftAssertions();
        Response apiResponse = World.getResponse();

        switch (this.data) {
            case "typeType":
                if (apiResponse.statusCode() == 200) {
                    Type apiType = apiResponse.as(Type.class);
                    Integer id = apiType.getId();
                    Type dbType = databaseimpl.getTypeById(id);

                    softAssert.assertThat(apiType)
                            .isEqualTo(dbType);
                } else if (apiResponse.statusCode() == 204) {
                    Type dbType = databaseimpl.getTypeById(World.getTypeResponse().getId());
                    softAssert.assertThat(dbType.getName())
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

    public void updateType(String newName, String contentType, String data) {
        Type dbType;

        setContentType(contentType);
        dbType = World.getTypeResponse();
        typeBody.setName(newName);
        this.newName = newName;

        Object restBody;
        switch (data) {
            case "typeType":
                this.data = "typeType";
                restBody = typeBody;
                break;
            case "invalidContentType":
                this.data = "invalidContentType";
                restBody = typeBody.toString();
                break;
            case "invalidBody":
                this.data = "invalidBody";
                invalidType.setNam(newName);
                restBody = invalidType;
                break;
            default:
                throw new UnsupportedOperationException();
        }

        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(this.type)
                .body(restBody)
                .put(Endpoints.TYPES + "/" + dbType.getId().toString()).then().log().all().and().extract().response();
        World.setResponse(response);
    }

    public void deleteType() {
        String queryId = "/" + World.getTypeResponse().getId().toString();
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .delete(Endpoints.TYPES + queryId);
        World.setResponse(response);
    }

    public void checkDeletedType() {

        SoftAssertions softAssert = new SoftAssertions();
        Type nullType = new Type();
        Type dbType = databaseimpl.getTypeById(World.getTypeResponse().getId());

        softAssert.assertThat(dbType)
                .isEqualTo(nullType);
        softAssert.assertAll();
    }

    public void deleteNonexistentType(String type) {
        if(Objects.equals(type, "nonExistentTypeTest")) {
            int id = databaseimpl.getMaxId("types") + 1;
            String queryId = "/" + String.valueOf(id);
            response = rest()
                    .baseUri(Endpoints.BASE_URL)
                    .contentType(ContentType.JSON)
                    .delete(Endpoints.TYPES + queryId);
            World.setResponse(response);
        }else {
            throw new UnsupportedOperationException();

        }
    }

    public void verifyInvalidGetById() {
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(World.getResponse().asString())
                .isEmpty();
        softAssert.assertAll();
    }

    public void getTypeById() {
        String query = "/" + World.getTypeResponse().getId().toString();
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.TYPES + query);
        World.setResponse(response);
    }

    public void getTypeById(String type) {
        if (Objects.equals(type, "invalidGetById")) {
            int id = databaseimpl.getMaxId("types") +1;
            String query = "/" + id;
            response = rest()
                    .baseUri(Endpoints.BASE_URL)
                    .contentType(ContentType.JSON)
                    .get(Endpoints.TYPES + query);
            World.setResponse(response);
        }else {
            throw new UnsupportedOperationException();

        }
    }

    public void checkGetByIdType(){
        SoftAssertions softAssert = new SoftAssertions();

        Type apiType = World.getTypeResponse();
        Type dbType = databaseimpl.getTypeById(apiType.getId());

        softAssert.assertThat(dbType)
                .isEqualTo(apiType);
        softAssert.assertAll();
    }

}
