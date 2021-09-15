package models.Pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Specialty.Specialty;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Body {

    private int id;

    private String name;

    private String birthDate;

    private int typeId;

    private int ownerId;


}
