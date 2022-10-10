package by.lukyanov.departmentservice.service;

import by.lukyanov.departmentservice.model.Department;

public interface DepartmentService extends BaseService<Department>{

    Department findByCode(String code);
}
