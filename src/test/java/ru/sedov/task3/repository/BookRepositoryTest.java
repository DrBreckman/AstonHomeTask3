package ru.sedov.task3.repository;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sedov.task3.config.SpringConfig;
import ru.sedov.task3.entity.Book;
import ru.sedov.task3.entity.User;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;

public class BookRepositoryTest extends RepositoryBasicTest<BookRepository> {

    public BookRepositoryTest() {
        super(BookRepository.class);
    }

    @Test
    public void testFindByName() {

        var book = getTestingRepository().findByName("On Cold Blood");

        assertAll(
            () -> assertNull(getTestingRepository().findByName("Java 8")),
            () -> assertNotNull(book),
            () -> assertEquals("On Cold Blood", book.getName()),
            () -> assertEquals("Truman Capote", book.getAuthor())
        );
    }

    @Test
    public void testFindAllByReaderName() {

        String readerName = "Nikita";
        var books = getTestingRepository().findAllByReaderName(readerName);

        assertAll(
            () -> assertTrue(getTestingRepository().findAllByReaderName("Magdalena").isEmpty()),
            () -> assertFalse(books.isEmpty()),
            () -> {
                for(var book : books) {
                    assertNotNull(book.getName());
                    assertNotNull(book.getAuthor());
                    assertFalse(book.getName().isEmpty());
                    assertFalse(book.getAuthor().isEmpty());
                    assertTrue(book.getReadingUsers().stream()
                        .anyMatch(x -> x.getName().equals(readerName)));
                }
            }
        );
    }

    @Override
    public void initDb() {
        List<User> users = new ArrayList<>() {{
            add(new User("Nikita", 25, "Gomel"));
            add(new User("Vlada", 24, "Moscow"));
        }};

        List<Book> books = new ArrayList<>() {{
            add(new Book("Pride and Prejudice", "Jane Austen"));
            add(new Book("To Kill a Mockingbird", "Harper Lee"));
            add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
            add(new Book("One Hundred Years of Solitude", "Gabriel Garcia Marquez"));
            add(new Book("On Cold Blood", "Truman Capote"));
        }};

        link(books.get(0), users.get(0));
        link(books.get(0), users.get(1));
        link(books.get(1), users.get(1));
        link(books.get(2), users.get(0));
        link(books.get(3), users.get(0));
        link(books.get(3), users.get(1));
        link(books.get(4), users.get(0));

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.saveAll(users);

        getTestingRepository().saveAll(books);
    }

    private void link(Book book, User user){
        book.getReadingUsers().add(user);
        user.getReadingBooks().add(book);
    }

}