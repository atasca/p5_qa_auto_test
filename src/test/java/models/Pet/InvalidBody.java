package models.Pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvalidBody {

    private Integer id;

    private String name;

    private Integer typeId;

    private Integer ownerId;
}
