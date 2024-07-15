package ru.sedov.task3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sedov.task3.dto.UserDto;
import ru.sedov.task3.mapper.impl.UserMapper;
import ru.sedov.task3.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<UserDto> getAll() {

        return UserMapper.INSTANCE.sourceToDestination(userRepository.findAll());
    }

    public List<UserDto> findAllWithGoodAverageMarkReview() {

        return UserMapper.INSTANCE.sourceToDestination(userRepository.findAllWithGoodAverageMarkReview());
    }
}
