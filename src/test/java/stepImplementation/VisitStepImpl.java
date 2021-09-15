package stepImplementation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import models.Vet.GetDBResponse;
import models.Vet.GetVetApiResponse;
import models.Vet.InvalidBody;
import models.Visit.Visit;
import models.Visit.VisitPet;
import models.Visit.VisitType;
import models.Visit.DbBody;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.groups.Tuple;
import rest.Endpoints;
import util.World;

import java.util.Objects;

import static net.serenitybdd.rest.SerenityRest.rest;

public class VisitStepImpl {

    private Response response;
    private DatabaseStepImpl databaseimpl = new DatabaseStepImpl();
    private Visit visitBody = Visit.builder().build();
    private InvalidBody invalidVisit = InvalidBody.builder().build();
    private ContentType type;
    private String data;
    private String newFirstName;
    private String createDescription ;

    public void getVet() {

        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.VISITS);
        World.setResponse(response);

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

    public void addVisit(String visit, String contentType, String data) {

        setContentType(contentType);
        visitBody.setDescription(visit);
        this.createDescription = visit;

        Object restBody;
        switch (data) {
            case "visitType":
                this.data = "visitType";
                VisitType type = new VisitType();
                type.setId(1);
                VisitPet pet = new VisitPet();
                pet.setId(2);
                pet.setType(type);
                visitBody.setPet(pet);
                restBody = visitBody;
                break;
            case "invalidContentType":
                this.data = "invalidContentType";
                restBody = visitBody.toString();
                break;
            case "invalidBody":
                this.data = "invalidBody"; // no pet included
                restBody = visitBody;
                break;
            default:
                throw new UnsupportedOperationException();
        }

        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(this.type)
                .body(restBody)
                .post(Endpoints.VISITS).then().log().all().and().extract().response();
        World.setResponse(response);
    }

    public void checkVisit() {
        SoftAssertions softAssert = new SoftAssertions();
        Response apiResponse = World.getResponse();

        switch (this.data) {
            case "visitType":
                if (apiResponse.statusCode() == 200) {
                    Visit apiVisit = apiResponse.as(Visit.class);
                    Integer id = apiVisit.getId();
                    DbBody dbVisit = databaseimpl.getVisitById(id);

                    softAssert.assertThat(apiVisit)
                            .isEqualTo(dbVisit);
                } else if (apiResponse.statusCode() == 204) {
                    DbBody dbVisit = databaseimpl.getVisitById(World.getVetResponse().getId());
                    softAssert.assertThat(dbVisit.getDescription())
                            .isEqualTo(this.createDescription);
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

    public void updateVisit(String description, String contentType, String data) {

        DbBody dbVisit;

        setContentType(contentType);
        dbVisit = World.getVisitResponse();
        visitBody.setDescription(description);
        this.newFirstName = description;
        this.createDescription = description;

        Object restBody;
        switch (data) {
            case "visitType":
                this.data = "visitType";
                VisitType type = new VisitType();
                type.setId(1);
                VisitPet pet = new VisitPet();
                pet.setId(2);
                pet.setType(type);
                visitBody.setPet(pet);
                restBody = visitBody;
                break;
            case "invalidContentType":
                this.data = "invalidContentType";
                restBody = visitBody.toString();
                break;
            case "invalidBody":
                this.data = "invalidBody";
                restBody = visitBody;
                break;
            default:
                throw new UnsupportedOperationException();
        }
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(this.type)
                .body(restBody)
                .put(Endpoints.VISITS + "/" + dbVisit.getId()).then().log().all().and().extract().response();
        World.setResponse(response);
    }


    public void deleteVisit() {
        String queryId = "/" + World.getVisitResponse().getId();
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .delete(Endpoints.VISITS + queryId);
        World.setResponse(response);
    }

    public void deleteNonexistentVisit(String visit) {
        if (Objects.equals(visit, "nonExistentVisitTest")) {
            int id = databaseimpl.getMaxId("visits") + 1;
            String queryId = "/" + id;
            response = rest()
                    .baseUri(Endpoints.BASE_URL)
                    .contentType(ContentType.JSON)
                    .delete(Endpoints.VISITS + queryId);
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

    public void checkDeletedVisit() {
        SoftAssertions softAssert = new SoftAssertions();

        DbBody nullVisit = new DbBody();
        nullVisit.setDate(null);
        nullVisit.setId(null);
        nullVisit.setPetId(null);
        nullVisit.setDescription(null);
        DbBody dbVisit = databaseimpl.getVisitById(World.getVisitResponse().getId());

        softAssert.assertThat(dbVisit)
                .isEqualTo(nullVisit);
        softAssert.assertAll();
    }


    public void getVisitById() {
        String query = "/" + World.getVisitResponse().getId().toString();
        response = rest()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .get(Endpoints.VISITS + query);
        World.setResponse(response);
    }

    public void getVisitById(String visit) {
        if (Objects.equals(visit, "invalidGetById")) {
            int id = databaseimpl.getMaxId("visits") + 1;
            String query = "/" + String.valueOf(id);
            response = rest()
                    .baseUri(Endpoints.BASE_URL)
                    .contentType(ContentType.JSON)
                    .get(Endpoints.VISITS + query);
            World.setResponse(response);
        } else {
            throw new UnsupportedOperationException();

        }
    }

    public void checkGetByIdVisit() {
        SoftAssertions softAssert = new SoftAssertions();

        DbBody apiVisit = World.getResponse().as(DbBody.class);
        DbBody dbVisit = databaseimpl.getVisitById(apiVisit.getId());

        softAssert.assertThat(apiVisit)
                .isEqualTo(dbVisit);
        softAssert.assertAll();
    }
}
