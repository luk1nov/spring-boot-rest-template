package by.lukyanov.userservice.service.client;

import by.lukyanov.userservice.dto.DepartmentDto;
import by.lukyanov.userservice.exception.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "department-service", configuration = CustomErrorDecoder.class)
public interface DepartmentApiClient {
    @GetMapping(value = "/api/departments/{id}")
    DepartmentDto getDepartmentById(@PathVariable Long id);
}
