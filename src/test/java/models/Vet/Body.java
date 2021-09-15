package models.Vet;

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

    private String firstName;

    private String lastName;

    private List<Specialty> specialties;

}
