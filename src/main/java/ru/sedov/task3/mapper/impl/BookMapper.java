package ru.sedov.task3.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.sedov.task3.dto.BookDto;
import ru.sedov.task3.entity.Book;
import ru.sedov.task3.entity.User;
import ru.sedov.task3.mapper.EntityMapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface BookMapper extends EntityMapper<Book, BookDto> {

    BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );

    @Override
    @Mapping(source = "readingUsers", target = "readerSet", qualifiedByName = "getUserNames")
    BookDto sourceToDestination(Book source);

    @Named("getUserNames")
    default Set<String> userSourceToUserNames(Set<User> source) {
        return source.stream()
            .map(User::getName)
            .collect(Collectors.toSet());
    }
}
