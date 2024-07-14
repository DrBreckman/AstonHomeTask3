package ru.sedov.task3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer mark;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Review() {

    }

    public Review(Integer mark, String description) {

        this.mark = mark;
        this.description = description;
    }

    public Review(Integer mark, String description, User user, Book book) {

        this.mark = mark;
        this.description = description;
        this.user = user;
        this.book = book;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Integer getMark() {

        return mark;
    }

    public void setMark(Integer mark) {

        this.mark = mark;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }

    public Book getBook() {

        return book;
    }

    public void setBook(Book book) {

        this.book = book;
    }
}
