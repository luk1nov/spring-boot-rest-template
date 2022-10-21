package by.lukyanov.departmentservice.service.impl;

import by.lukyanov.departmentservice.exception.DepartmentNotEmptyException;
import by.lukyanov.departmentservice.model.Department;
import by.lukyanov.departmentservice.repository.DepartmentRepository;
import by.lukyanov.departmentservice.service.DepartmentService;
import by.lukyanov.departmentservice.service.client.UserApiClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserApiClient userApiClient;

    @Override
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Department findById(Long id) {
        return departmentRepository.findById(requireNonNull(id))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public Department update(Long id, Department departmentForUpdate) {
        Department updatedDepartment = setDepartmentProperties(findById(id), departmentForUpdate);
        return departmentRepository.save(updatedDepartment);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Department department = findById(requireNonNull(id));
        Boolean exist = userApiClient.isUserInDepartment(id);
        if(nonNull(exist) && exist){
            log.info("Can not delete department with users");
            throw new DepartmentNotEmptyException("Can not delete department with users");
        } else {
            departmentRepository.delete(department);
        }
    }

    @Override
    public Department findByCode(String code) {
        return departmentRepository.findByDepartmentCode(code).orElseThrow(EntityNotFoundException::new);
    }

    private Department setDepartmentProperties(Department existed, Department updated){
        String name = updated.getDepartmentName();
        String address = updated.getDepartmentAddress();
        String code = updated.getDepartmentCode();
        if(nonNull(name)){
            existed.setDepartmentName(name);
        }
        if(nonNull(address)){
            existed.setDepartmentName(address);
        }
        if(nonNull(code)){
            existed.setDepartmentName(code);
        }
        return existed;
    }
}
