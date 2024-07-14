package ru.sedov.task3.dto;

import java.util.Set;

public class BookDto {

    private String name;
    private String author;

    private Set<String> readerSet;

    public BookDto() {

    }

    public BookDto(String name, String author) {

        this.name = name;
        this.author = author;
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

    public Set<String> getReaderSet() {

        return readerSet;
    }

    public void setReaderSet(Set<String> readerSet) {

        this.readerSet = readerSet;
    }

    @Override
    public String toString() {

        return "BookDto{" +
            "name='" + name + '\'' +
            ", author='" + author + '\'' +
            ", readerSet=" + readerSet +
            '}';
    }
}
