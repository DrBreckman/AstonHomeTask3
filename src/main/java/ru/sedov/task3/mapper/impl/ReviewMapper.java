package ru.sedov.task3.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.sedov.task3.dto.ReviewDto;
import ru.sedov.task3.entity.Review;
import ru.sedov.task3.mapper.EntityMapper;

import java.util.List;

@Mapper
public interface ReviewMapper extends EntityMapper<Review, ReviewDto> {

    ReviewMapper INSTANCE = Mappers.getMapper( ReviewMapper.class );

    @Override
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "book.name", target = "bookName")
    ReviewDto sourceToDestination(Review source);

    @Override
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "book.name", target = "bookName")
    List<ReviewDto> sourceToDestination(List<Review> source);
}
