package ru.sedov.task3.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sedov.task3.dto.UserDto;
import ru.sedov.task3.entity.User;
import ru.sedov.task3.mapper.EntityMapper;

@Mapper
public interface UserMapper extends EntityMapper<User, UserDto> {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Override
    @Mapping(source = "readingBooks", target = "readingBooksSet")
    UserDto sourceToDestination(User source);
}

