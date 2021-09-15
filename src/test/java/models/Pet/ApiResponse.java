package models.Pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Owner;
import models.PetOwner;
import models.PetType.Type;
import models.Visit.PetVisit;


import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private int id;

    private String name;

    private String birthDate;

    private Type type;

    private PetOwner owner;

    private List<PetVisit> visits;

}
