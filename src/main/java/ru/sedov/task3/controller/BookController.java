package ru.sedov.task3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sedov.task3.dto.BookDto;
import ru.sedov.task3.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService service) {

        this.service = service;
    }

    @GetMapping("/all")
    public List<BookDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/bookByName")
    public BookDto getByName(@RequestBody String name) {


        return service.getByName(name);
    }

    @GetMapping("/allByReader")
    public List<BookDto> getByReaderName(@RequestBody String name) {
        return service.getByReaderName(name);
    }


    @PostMapping("/save")
    public BookDto saveBook(@RequestBody BookDto book){

        return service.saveBook(book);
    }
}
