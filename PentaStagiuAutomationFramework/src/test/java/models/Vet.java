package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vet {
    private String firstName = " ";
    private Integer id = 1;
    private String lastName = " ";
   // private List<Speciality> speciality;
    private Speciality speciality;
}
