package by.lukyanov.departmentservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "department_name", nullable = false)
    @NotBlank(message = "Department name is mandatory")
    private String departmentName;

    @Column(name = "department_address")
    @Length(max = 255)
    private String departmentAddress;

    @Column(name = "department_code", nullable = false, unique = true)
    @NotNull(message = "Department code is mandatory")
    @Pattern(regexp = "^\\p{Upper}\\d{3}$", message = "Invalid department code")
    private String departmentCode;
}
