package models.Vet;

import groovy.transform.Sortable;
import groovy.transform.ToString;
import lombok.*;
import models.Specialty.Specialty;
import org.jetbrains.annotations.NotNull;

import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Builder
public class GetVetApiResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private List<Specialty> specialties;


}