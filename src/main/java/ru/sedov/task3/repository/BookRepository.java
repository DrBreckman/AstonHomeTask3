package ru.sedov.task3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sedov.task3.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByName(String name);

    @Query("""
        SELECT b
        FROM Book b
        JOIN b.readingUsers u
        WHERE u.name = ?1
    """)
    List<Book> findAllByReaderName(String readerName);
}
