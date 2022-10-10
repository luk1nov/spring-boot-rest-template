package by.lukyanov.userservice.mapper;

import by.lukyanov.userservice.dto.UserDto;
import by.lukyanov.userservice.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    User dtoToUser(UserDto userDto);
    UserDto userToDto(User user);
}
