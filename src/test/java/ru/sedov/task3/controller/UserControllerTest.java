package ru.sedov.task3.controller;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.sedov.task3.dto.BookDto;
import ru.sedov.task3.dto.ReviewDto;
import ru.sedov.task3.dto.UserDto;
import ru.sedov.task3.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest extends TestCase {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Test
    public void testGetUsers() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        List<UserDto> users = new ArrayList<>() {{
            add(new UserDto("Nikita", 25, "Gomel"));
            add(new UserDto("Dasha", 23, "Berlin"));
            add(new UserDto("Vlada", 24, "Moscow"));
            add(new UserDto("Max", 26, "Minsk"));
        }};

        users.forEach(user -> {
            user.setReviewSet(new HashSet<>(){{
                add(new ReviewDto(3, "nice"));
                add(new ReviewDto(4, "good"));
            }});
            user.setReadingBooksSet(new HashSet<>(){{
                add(new BookDto("Pride and Prejudice", "Jane Austen"));
                add(new BookDto("To Kill a Mockingbird", "Harper Lee"));
            }});
        });

        Mockito.when(userService.getAll()).thenReturn(users);

        mockMvc.perform(get("/user/all")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").hasJsonPath())
            .andExpect(result -> assertEquals(result.getResponse().getContentAsString(),
                mapper.writeValueAsString(users)));

        verify(userService, times(1)).getAll();
    }

    @Test
    public void testGetUsersWithGoodAverageMark() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        List<UserDto> users = new ArrayList<>() {{
            add(new UserDto("Nikita", 25, "Gomel"));
            add(new UserDto("Dasha", 23, "Berlin"));
            add(new UserDto("Vlada", 24, "Moscow"));
            add(new UserDto("Max", 26, "Minsk"));
        }};

        Mockito.when(userService.findAllWithGoodAverageMarkReview()).thenReturn(users);

        mockMvc.perform(get("/user/goodAverage")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").hasJsonPath())
            .andExpect(result -> assertEquals(result.getResponse().getContentAsString(),
                mapper.writeValueAsString(users)));

        verify(userService, times(1)).findAllWithGoodAverageMarkReview();
    }
}