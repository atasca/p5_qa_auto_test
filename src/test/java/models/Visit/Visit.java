package models.Visit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Pet.Pet;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Visit {
    private String date = "2021/08/23";
    private String description = "vizita la veterinar";
    private int id = 0;
    private VisitPet pet;
}

