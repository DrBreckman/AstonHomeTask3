package ru.sedov.task3.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sedov.task3.entity.Book;
import ru.sedov.task3.entity.Review;
import ru.sedov.task3.mapper.impl.ReviewMapper;
import ru.sedov.task3.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceTest extends TestCase {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Test
    public void testGetAll() {
        List<Review> reviews = new ArrayList<>() {{
            add(new Review(2, "Bad"));
            add(new Review(3, "Norm"));
            add(new Review(4, "Good"));
        }};

        Mockito.when(reviewRepository.findAll()).thenReturn(reviews);

        for(var review : ReviewMapper.INSTANCE.destinationToSource(reviewService.getAll())) {
            assertNotNull(reviews.stream()
                .filter(x -> x.getMark().equals(review.getMark()))
                .findFirst()
                .orElse(null));
        }
    }

    @Test
    public void testGetBestReviews() {
        List<Review> reviews = new ArrayList<>() {{
            add(new Review(5, "Super"));
            add(new Review(5, "Perfect"));
            add(new Review(5, "Awesome"));
        }};
        reviews.forEach(x -> {
            x.setId(1L);
        });

        Mockito.when(reviewRepository.findAllBestReviews()).thenReturn(reviews);

        for(var review : ReviewMapper.INSTANCE.destinationToSource(reviewService.getBestReviews())) {
            assertNotNull(reviews.stream()
                .filter(x -> x.getMark().equals(review.getMark()))
                .findFirst()
                .orElse(null));
            assertNull(review.getId());
        }
    }

    @Test
    public void testGetWorstReviews() {
        List<Review> reviews = new ArrayList<>() {{
            add(new Review(2, "Bad"));
            add(new Review(2, "FF"));
            add(new Review(2, "Very bad"));
        }};
        reviews.forEach(x -> x.setBook(new Book("name", "author")));

        Mockito.when(reviewRepository.findAllWorstReviews()).thenReturn(reviews);

        for(var review : ReviewMapper.INSTANCE.destinationToSource(reviewService.getWorstReviews())) {
            assertNotNull(reviews.stream()
                .filter(x -> x.getMark().equals(review.getMark()))
                .findFirst()
                .orElse(null));
        }
    }
}