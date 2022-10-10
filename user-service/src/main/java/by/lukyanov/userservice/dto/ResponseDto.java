package by.lukyanov.userservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"user", "department"})
public class ResponseDto {
    private DepartmentDto department;
    private UserDto user;
}
