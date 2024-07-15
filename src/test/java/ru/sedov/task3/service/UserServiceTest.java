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
import ru.sedov.task3.entity.User;
import ru.sedov.task3.mapper.impl.UserMapper;
import ru.sedov.task3.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends TestCase {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetAll() {
        List<User> users = new ArrayList<>() {{
            add(new User("Nikita", 25, "Gomel"));
            add(new User("Vlada", 24, "Moscow"));
            add(new User("Dasha", 22, "Berlin"));
        }};

        Mockito.when(userRepository.findAll()).thenReturn(users);

        for(var review : UserMapper.INSTANCE.destinationToSource(userService.getAll())) {
            assertNotNull(users.stream()
                .filter(x -> x.getName().equals(review.getName()))
                .findFirst()
                .orElse(null));
        }
    }

    @Test
    public void testFindAllWithGoodAverageMarkReview() {
        List<User> users = new ArrayList<>() {{
            add(new User("Nikita", 25, "Gomel"));
            add(new User("Vlada", 24, "Moscow"));
            add(new User("Dasha", 22, "Berlin"));
        }};
        users.forEach(x -> {
            x.setId(1L);
            x.setReviewSet(new HashSet<>());
            x.setReadingBooks(new HashSet<>());
        });

        Mockito.when(userRepository.findAllWithGoodAverageMarkReview()).thenReturn(users);

        for(var review : UserMapper.INSTANCE.destinationToSource(userService.findAllWithGoodAverageMarkReview())) {
            assertNotNull(users.stream()
                .filter(x -> x.getName().equals(review.getName()))
                .findFirst()
                .orElse(null));
            assertNull(review.getId());
            assertTrue(review.getReviewSet().isEmpty());
            assertTrue(review.getReadingBooks().isEmpty());
        }
    }
}