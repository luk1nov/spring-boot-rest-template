package by.lukyanov.userservice.service.client;

import by.lukyanov.userservice.dto.DepartmentDto;
import by.lukyanov.userservice.exception.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "DEPARTMENT-SERVICE", url = "http://localhost:8081/api/departments/",
configuration = CustomErrorDecoder.class)
public interface  DepartmentApiClient {
    @GetMapping(value = "{id}")
    DepartmentDto getDepartmentById(@PathVariable Long id);
}
