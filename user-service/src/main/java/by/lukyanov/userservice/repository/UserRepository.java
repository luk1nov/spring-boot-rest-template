package by.lukyanov.userservice.repository;

import by.lukyanov.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsAllByDepartmentId(Long id);
}
