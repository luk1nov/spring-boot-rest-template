package by.lukyanov.departmentservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "USER-SERVICE", url = "http://localhost:8082/api/users")
public interface UserApiClient {

    @GetMapping("/exist-in-department/{id}")
    Boolean isUserInDepartment(@PathVariable("id") Long departmentId);
}
