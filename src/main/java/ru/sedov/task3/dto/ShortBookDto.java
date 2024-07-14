package ru.sedov.task3.dto;

public class ShortBookDto {

    private String name;
    private String author;

    public ShortBookDto() {

    }

    public ShortBookDto(String name, String author) {

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

    @Override
    public String toString() {

        return "ShortBookDto{" +
            "name='" + name + '\'' +
            ", author='" + author + '\'' +
            '}';
    }
}
