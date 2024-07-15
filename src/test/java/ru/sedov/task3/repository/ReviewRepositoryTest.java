package ru.sedov.task3.repository;

import org.junit.Test;
import ru.sedov.task3.entity.Review;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

public class ReviewRepositoryTest extends RepositoryBasicTest<ReviewRepository> {

    public ReviewRepositoryTest() {
        super(ReviewRepository.class);
    }

    @Test
    public void testFindAllBestReviews() {
        Integer maxMark = getTestingRepository().findAll().stream()
            .max(Comparator.comparingInt(Review::getMark))
            .orElse(new Review(-1, "none"))
            .getMark();

        var reviews = getTestingRepository().findAllBestReviews();
        assertAll(
            () -> assertFalse(reviews.isEmpty()),
            () -> {
                for(var review : reviews) {
                    assertEquals(maxMark, review.getMark());
                }
            }
        );
    }

    @Test
    public void testFindAllWorstReviews() {
        Integer minMark = getTestingRepository().findAll().stream()
            .min(Comparator.comparingInt(Review::getMark))
            .orElse(new Review(-1, "none"))
            .getMark();

        var reviews = getTestingRepository().findAllWorstReviews();
        assertAll(
            () -> assertFalse(reviews.isEmpty()),
            () -> {
                for(var review : reviews) {
                    assertEquals(minMark, review.getMark());
                }
            }
        );
    }

    @Override
    public void initDb() {
        List<Review> reviews = new ArrayList<>() {{
            add(new Review(2, "very bad"));
            add(new Review(2, "olso very bad"));
            add(new Review(3, "norm"));
            add(new Review(4, "good"));
            add(new Review(4, "very good"));
            add(new Review(4, "super norm"));
            add(new Review(5, "Perfect!"));
            add(new Review(5, "best of thr best"));
        }};

        getTestingRepository().saveAll(reviews);
    }


}