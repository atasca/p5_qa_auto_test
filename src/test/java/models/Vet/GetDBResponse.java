package models.Vet;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class GetDBResponse {

    private Integer id;

    private String firstName;

    private String lastName ;
}
