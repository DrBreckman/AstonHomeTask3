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
import ru.sedov.task3.entity.Book;
import ru.sedov.task3.mapper.impl.BookMapper;
import ru.sedov.task3.service.BookService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest extends TestCase {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        List<BookDto> books = new ArrayList<>() {{
                add(new BookDto("Pride and Prejudice", "Jane Austen"));
                add(new BookDto("To Kill a Mockingbird", "Harper Lee"));
                add(new BookDto("The Great Gatsby", "F. Scott Fitzgerald"));
                add(new BookDto("One Hundred Years of Solitude", "Gabriel Garcia Marquez"));
                add(new BookDto("On Cold Blood", "Truman Capote"));
            }};

        Mockito.when(bookService.getAll()).thenReturn(books);

        mockMvc.perform(get("/book/all")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").hasJsonPath())
            .andExpect(result -> assertEquals(result.getResponse().getContentAsString(),
                mapper.writeValueAsString(books)));
        assertTrue(books.get(0).getReaderSet().isEmpty());

        verify(bookService, times(1)).getAll();
    }

    @Test
    public void testGetByName() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        BookDto book = BookMapper.INSTANCE.sourceToDestination(new Book("The Great Gatsby", "F. Scott Fitzgerald"));

        Mockito.when(bookService.getByName("The Great Gatsby")).thenReturn(book);

        mockMvc.perform(get("/book/byName")
                .accept(MediaType.APPLICATION_JSON)
                .content("The Great Gatsby"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("The Great Gatsby"))
            .andExpect(jsonPath("$.author").value("F. Scott Fitzgerald"));

        verify(bookService, times(1)).getByName("The Great Gatsby");
    }

    @Test
    public void testGetByReaderName() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        List<BookDto> books = new ArrayList<>(){{
            add(new BookDto("Pride and Prejudice", "Jane Austen"));
            add(new BookDto("To Kill a Mockingbird", "Harper Lee"));
            add(new BookDto("The Great Gatsby", "F. Scott Fitzgerald"));
        }};

        Mockito.when(bookService.getByReaderName("Nikita")).thenReturn(books);

        mockMvc.perform(get("/book/allByReader")
                .accept(MediaType.APPLICATION_JSON)
                .content("Nikita"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").hasJsonPath())
            .andExpect(result -> assertEquals(result.getResponse().getContentAsString(),
                mapper.writeValueAsString(books)));

        verify(bookService, times(1)).getByReaderName("Nikita");
    }
}