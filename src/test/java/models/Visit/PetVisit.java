package models.Visit;

import lombok.*;
import models.Pet.Pet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PetVisit {
    private String date = "2021/08/23";
    private String description = "vizita la veterinar";
    private int id = 0;
}
