package ru.sedov.task3.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.sedov.task3.dto.ShortReviewDto;
import ru.sedov.task3.entity.Review;
import ru.sedov.task3.mapper.EntityMapper;

@Mapper
public interface ShortReviewMapper extends EntityMapper<Review, ShortReviewDto> {

    ShortReviewMapper INSTANCE = Mappers.getMapper( ShortReviewMapper.class );
}
