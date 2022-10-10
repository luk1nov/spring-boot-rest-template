package by.lukyanov.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;
}
