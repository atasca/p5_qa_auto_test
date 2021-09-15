package models.PetType;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Type {

    private Integer id;

    private String name;
}