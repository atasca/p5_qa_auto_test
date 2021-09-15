package models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter

public class User {
    private String username ="user123";
    private String password ="pisica";
    private boolean enabled = true;
}
