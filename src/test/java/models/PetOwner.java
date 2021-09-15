package models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
public class PetOwner {

    private String id;

    private String address = "str. Test nr 10";

    private String city = "Arad";

    private String firstName = "";

    private String lastName = "";

    private String telephone = "0764321456";
}
