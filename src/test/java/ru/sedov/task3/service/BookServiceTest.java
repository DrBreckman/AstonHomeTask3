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
import ru.sedov.task3.mapper.impl.BookMapper;
import ru.sedov.task3.repository.BookRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest extends TestCase {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testGetAll() {
        List<Book> books = new ArrayList<>() {{
            add(new Book("Pride and Prejudice", "Jane Austen"));
            add(new Book("To Kill a Mockingbird", "Harper Lee"));
            add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
            add(new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez"));
            add(new Book("On Cold Blood", "Truman Capote"));
        }};

        Mockito.when(bookRepository.findAll()).thenReturn(books);

        for(var book : BookMapper.INSTANCE.destinationToSource(bookService.getAll())) {
            assertNotNull(books.stream()
                .filter(x -> x.getName().equals(book.getName()))
                .findFirst()
                .orElse(null));
        }
    }

    @Test
    public void testGetByName() {

        String name = "On Cold Blood";
        Book book = new Book(name, "Truman Capote");
        book.setId(1L);
        book.setReadingUsers(new HashSet<>());
        book.setReviewSet(new HashSet<>());

        Mockito.when(bookRepository.findByName(name)).thenReturn(book);

        var dto = BookMapper.INSTANCE.destinationToSource(bookService.getByName(name));

        assertEquals(book.getName(), dto.getName());
        assertEquals(book.getAuthor(), dto.getAuthor());
        assertNull(dto.getId());
        assertEquals(book.getReviewSet(), dto.getReviewSet());
        assertEquals(book.getReadingUsers(), dto.getReadingUsers());
    }

    @Test
    public void testGetByReaderName() {
        List<Book> books = new ArrayList<>() {{
            add(new Book("Pride and Prejudice", "Jane Austen"));
            add(new Book("To Kill a Mockingbird", "Harper Lee"));
            add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        }};

        Mockito.when(bookRepository.findAllByReaderName("Nikita")).thenReturn(books);

        for(var book : BookMapper.INSTANCE.destinationToSource(bookService.getByReaderName("Nikita"))) {
            assertNotNull(books.stream()
                .filter(x -> x.getName().equals(book.getName()))
                .findFirst()
                .orElse(null));
        }
    }
}