package ru.sedov.task3.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String author;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Review> reviewSet;

    @ManyToMany(fetch = FetchType.EAGER,
                cascade = CascadeType.ALL,
                mappedBy = "readingBooks")
    private Set<User> readingUsers;

    public Book() {

    }

    public Book(String name, String author) {

        this.name = name;
        this.author = author;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public Set<Review> getReviewSet() {

        return reviewSet;
    }

    public void setReviewSet(Set<Review> reviewSet) {

        this.reviewSet = reviewSet;
    }

    public Set<User> getReadingUsers() {

        return readingUsers;
    }

    public void setReadingUsers(Set<User> readingUsers) {

        this.readingUsers = readingUsers;
    }
}
