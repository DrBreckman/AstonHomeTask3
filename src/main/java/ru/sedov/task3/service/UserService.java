package ru.sedov.task3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sedov.task3.dto.UserDto;
import ru.sedov.task3.entity.User;
import ru.sedov.task3.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<UserDto> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(x -> new UserDto(x.getName(), x.getAge(), x.getAddress())).toList();
    }
}
