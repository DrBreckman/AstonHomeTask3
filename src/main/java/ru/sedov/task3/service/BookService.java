package ru.sedov.task3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sedov.task3.dto.BookDto;
import ru.sedov.task3.mapper.impl.BookMapper;
import ru.sedov.task3.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {

        this.repository = repository;
    }

    public List<BookDto> getAll() {

        return BookMapper.INSTANCE.sourceToDestination(repository.findAll());
    }

    public BookDto getByName(String name) {
        return BookMapper.INSTANCE.sourceToDestination(repository.findByName(name));
    }

    public List<BookDto> getByReaderName(String name) {

        return BookMapper.INSTANCE.sourceToDestination(repository.findAllByReaderName(name));
    }
}
