package util;


import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import models.Owner;
import models.Pet.Pet;
import models.PetType.Type;
import models.Specialty.Specialty;
import models.Visit.DbBody;

public final class World {

  @Getter
  @Setter
  private static Response response;

  @Getter
  @Setter
  private static models.Vet.GetDBResponse vetResponse;

  @Getter
  @Setter
  private static Specialty specialtyResponse;

  @Getter
  @Setter
  private static Type typeResponse;

  @Getter
  @Setter
  private static Pet petResponse;

  @Getter
  @Setter
  private static DbBody visitResponse;

  @Getter
  @Setter
  private static Owner ownerResponse;
}
