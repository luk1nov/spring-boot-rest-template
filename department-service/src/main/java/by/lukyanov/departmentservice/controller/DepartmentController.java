package by.lukyanov.departmentservice.controller;

import by.lukyanov.departmentservice.model.Department;
import by.lukyanov.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public Department create(@RequestBody Department department){
        return departmentService.create(department);
    }

    @GetMapping
    public List<Department> findAll(@RequestParam(name = "code", required = false) String code){
        if (nonNull(code)){
            return List.of(departmentService.findByCode(code));
        } else {
            return departmentService.findAll();
        }
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id){
        return departmentService.findById(id);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department department){
        return departmentService.update(id, department);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        departmentService.deleteById(id);
    }
}
