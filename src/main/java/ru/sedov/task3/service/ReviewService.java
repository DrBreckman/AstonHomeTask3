package ru.sedov.task3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sedov.task3.dto.ReviewDto;
import ru.sedov.task3.mapper.impl.ReviewMapper;
import ru.sedov.task3.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    @Autowired
    public ReviewService(ReviewRepository repository) {

        this.repository = repository;
    }

    public List<ReviewDto> getAll() {

        return ReviewMapper.INSTANCE.sourceToDestination(repository.findAll());
    }

    public List<ReviewDto> getBestReviews() {

        return ReviewMapper.INSTANCE.sourceToDestination(repository.findAllBestReviews());
    }

    public List<ReviewDto> getWorstReviews() {

        return ReviewMapper.INSTANCE.sourceToDestination(repository.findAllWorstReviews());
    }
}
