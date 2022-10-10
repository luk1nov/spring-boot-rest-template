package by.lukyanov.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is mandatory")
    @Length(min = 2, max = 40)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is mandatory")
    @Length(min = 2, max = 40)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Invalid email")
    private String email;

    @Column(name = "department_id")
    private Long departmentId;
}
