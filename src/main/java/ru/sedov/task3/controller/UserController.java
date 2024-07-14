package ru.sedov.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sedov.task3.dto.UserDto;
import ru.sedov.task3.entity.User;
import ru.sedov.task3.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping("/all")
    public List<UserDto> getUsers(){

        return service.getUsers();
    }

    @PostMapping("/save")
    public UserDto saveUser(@RequestBody UserDto user){

        return service.saveUser(user);
    }
}
