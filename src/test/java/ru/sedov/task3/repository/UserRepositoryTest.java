package ru.sedov.task3.repository;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sedov.task3.config.SpringConfig;
import ru.sedov.task3.entity.Review;
import ru.sedov.task3.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

public class UserRepositoryTest extends RepositoryBasicTest<UserRepository> {

    public UserRepositoryTest() {
        super(UserRepository.class);
    }

    @Test
    public void testFindAllWithGoodAverageMarkReview() {

        var users = getTestingRepository().findAllWithGoodAverageMarkReview();

        assertAll(
            () -> assertFalse(users.isEmpty()),
            () -> {
                for(var user : users) {
                    int size = user.getReviewSet().size();
                    assertTrue(user.getReviewSet().stream()
                        .map(Review::getMark)
                        .reduce(Integer::sum)
                        .orElse(0) / size >= 3);
                }
            }
        );
    }

    @Override
    public void initDb() {
        List<User> users = new ArrayList<>() {{
            add(new User("Misha", 19, "Minsk"));
            add(new User("Masha", 20, "Brest"));
            add(new User("Dasha", 21, "Vologda"));
        }};

        List<Review> reviews = new ArrayList<>() {{
            add(new Review(3, "Good"));
            add(new Review(3, "Nice"));
            add(new Review(2, "Bad"));
            add(new Review(1, "Worst"));
            add(new Review(2, "Fi"));
            add(new Review(5, "Perfect"));
        }};

        link(users.get(0), reviews.get(0));
        link(users.get(0), reviews.get(1));
        link(users.get(1), reviews.get(2));
        link(users.get(1), reviews.get(3));
        link(users.get(2), reviews.get(4));
        link(users.get(2), reviews.get(5));

        getTestingRepository().saveAll(users);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        var reviewRepository = context.getBean(ReviewRepository.class);
        reviewRepository.saveAll(reviews);
    }

    private void link(User user, Review review){
        user.getReviewSet().add(review);
        review.setUser(user);
    }
}







