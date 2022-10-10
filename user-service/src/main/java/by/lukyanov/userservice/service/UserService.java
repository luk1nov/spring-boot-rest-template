package by.lukyanov.userservice.service;

import by.lukyanov.userservice.dto.ResponseDto;
import by.lukyanov.userservice.dto.UserDto;

public interface UserService extends BaseService<UserDto>{
    UserDto findByEmail(String code);

    ResponseDto findByIdWithFullInfo(Long id);

    Boolean existByDepartmentId(Long id);
}
