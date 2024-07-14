package ru.sedov.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sedov.task3.dto.ReviewDto;
import ru.sedov.task3.dto.UserDto;
import ru.sedov.task3.entity.Review;
import ru.sedov.task3.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {

        this.service = service;
    }

    @GetMapping("/all")
    public List<ReviewDto> getAll() {
        return service.getReviews();
    }

    @PostMapping("/save")
    public ReviewDto saveReview(@RequestBody ReviewDto review){

        return service.saveReview(review);
    }
}
