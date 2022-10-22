package by.lukyanov.userservice.service.impl;

import by.lukyanov.userservice.dto.DepartmentDto;
import by.lukyanov.userservice.dto.ResponseDto;
import by.lukyanov.userservice.dto.UserDto;
import by.lukyanov.userservice.exception.ExternalApiException;
import by.lukyanov.userservice.mapper.UserMapper;
import by.lukyanov.userservice.model.User;
import by.lukyanov.userservice.repository.UserRepository;
import by.lukyanov.userservice.service.client.DepartmentApiClient;
import by.lukyanov.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentApiClient departmentApiClient;
    private final UserMapper userMapper;

    @Override
    public UserDto create(UserDto user) {
        DepartmentDto departmentDto = departmentApiClient.getDepartmentById(user.getDepartmentId());
        if(departmentDto != null){
            return userMapper.userToDto(userRepository.save(userMapper.dtoToUser(requireNonNull(user))));
        } else {
            log.info("Department service didn't return successful response");
            throw new ExternalApiException(HttpStatus.NOT_FOUND,
                    "Department with specified ID not found. Can not create user entity");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ResponseDto findByIdWithFullInfo(Long id) {
        User user = userRepository.findById(requireNonNull(id)).orElseThrow(EntityNotFoundException::new);
        DepartmentDto departmentDto = departmentApiClient.getDepartmentById(user.getDepartmentId());
        if(departmentDto != null){
            return new ResponseDto(departmentDto, userMapper.userToDto(user));
        } else {
            log.info("Department service didn't return successful response");
            throw new ExternalApiException(HttpStatus.NOT_FOUND,
                    "Can not find user by id cause department service didn't return successful response");
        }
    }

    @Override
    public Boolean existByDepartmentId(Long id) {
        return userRepository.existsAllByDepartmentId(id);
    }

    public UserDto findById(Long id){
        return userMapper.userToDto(
                userRepository.findById(requireNonNull(id)).orElseThrow(EntityNotFoundException::new)
        );
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::userToDto).toList();
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserDto userForUpdate) {
        UserDto updatedDepartment = setUserProperties(findById(id), userForUpdate);
        return userMapper.userToDto(
                userRepository.save(userMapper.dtoToUser(updatedDepartment))
        );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if(nonNull(id) && userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Id is null or entity not exist");
        }
    }

    @Override
    public UserDto findByEmail(String code) {
        return userMapper.userToDto(userRepository.findByEmail(code).orElseThrow(EntityNotFoundException::new));
    }

    private UserDto setUserProperties(UserDto existed, UserDto updated){
        String firstName = updated.getFirstName();
        String lastName = updated.getLastName();
        String email = updated.getEmail();
        Long departmentId = updated.getDepartmentId();
        if(Objects.nonNull(firstName)){
            existed.setFirstName(firstName);
        }
        if(Objects.nonNull(lastName)){
            existed.setLastName(lastName);
        }
        if(Objects.nonNull(email)){
            existed.setEmail(email);
        }
        if(Objects.nonNull(departmentId)){
            existed.setDepartmentId(departmentId);
        }
        return existed;
    }
}
