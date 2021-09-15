package models.owners;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Pet.Pet;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class body {
    private String address = "str. Test nr 10";

    private String city = "Arad";

    private String firstName = "";

    private String lastName = "";

    private String telephone = "0764321456";

    private List<Pet> pets;

}
