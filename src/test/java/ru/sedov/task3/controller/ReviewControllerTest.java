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
import ru.sedov.task3.dto.ReviewDto;
import ru.sedov.task3.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ReviewControllerTest extends TestCase {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    private final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        List<ReviewDto> reviews = new ArrayList<>() {{
            add(new ReviewDto(2, "Bad"));
            add(new ReviewDto(3, "Norm"));
            add(new ReviewDto(4, "Good"));
            add(new ReviewDto(4, "very Good"));
            add(new ReviewDto(5, "Perfect"));
        }};

        Mockito.when(reviewService.getAll()).thenReturn(reviews);

        mockMvc.perform(get("/review/all")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").hasJsonPath())
            .andExpect(result -> assertEquals(result.getResponse().getContentAsString(),
                mapper.writeValueAsString(reviews)));

        verify(reviewService, times(1)).getAll();
    }

    @Test
    public void testGetBestReviews() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        List<ReviewDto> reviews = new ArrayList<>() {{
            add(new ReviewDto(5, "Good"));
            add(new ReviewDto(5, "Perfect"));
        }};

        Mockito.when(reviewService.getBestReviews()).thenReturn(reviews);

        mockMvc.perform(get("/review/best")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").hasJsonPath())
            .andExpect(result -> assertEquals(result.getResponse().getContentAsString(),
                mapper.writeValueAsString(reviews)));
        assertNull(reviews.get(0).getBookName());
        assertNull(reviews.get(0).getUserName());
        verify(reviewService, times(1)).getBestReviews();
    }

    @Test
    public void testGetWorstReviews() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        List<ReviewDto> reviews = new ArrayList<>() {{
            add(new ReviewDto(2, "Bad"));
            add(new ReviewDto(2, "Very bad"));
        }};

        Mockito.when(reviewService.getWorstReviews()).thenReturn(reviews);

        mockMvc.perform(get("/review/worst")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").hasJsonPath())
            .andExpect(result -> assertEquals(result.getResponse().getContentAsString(),
                mapper.writeValueAsString(reviews)));

        verify(reviewService, times(1)).getWorstReviews();
    }
}