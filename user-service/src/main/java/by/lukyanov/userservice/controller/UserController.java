package by.lukyanov.userservice.controller;

import by.lukyanov.userservice.dto.ResponseDto;
import by.lukyanov.userservice.dto.UserDto;
import by.lukyanov.userservice.model.User;
import by.lukyanov.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto create(@RequestBody UserDto user){
        return userService.create(user);
    }

    @GetMapping
    public List<UserDto> findAll(@RequestParam(name = "email", required = false) String email){
        if (nonNull(email)){
            return List.of(userService.findByEmail(email));
        } else {
            return userService.findAll();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id, @RequestParam(name = "full", required = false) boolean full){
        return full ? new ResponseEntity<>(userService.findByIdWithFullInfo(id), HttpStatus.OK) :
                new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto user){
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }

    @GetMapping("/exist-in-department/{id}")
    public Boolean existUsersInDepartment(@PathVariable Long id){
        return userService.existByDepartmentId(id);
    }

}
