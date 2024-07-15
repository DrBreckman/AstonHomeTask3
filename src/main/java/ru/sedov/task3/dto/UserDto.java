package ru.sedov.task3.dto;

import java.util.Set;

public class UserDto {

    private String name;
    private int age;
    private String address;

    private Set<ReviewDto> reviewSet;
    private Set<BookDto> readingBooksSet;

    public UserDto() {
    }

    public UserDto(String name, int age, String address) {

        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public Set<ReviewDto> getReviewSet() {

        return reviewSet;
    }

    public void setReviewSet(Set<ReviewDto> reviewSet) {

        this.reviewSet = reviewSet;
    }

    public Set<BookDto> getReadingBooksSet() {

        return readingBooksSet;
    }

    public void setReadingBooksSet(Set<BookDto> readingBooksSet) {

        this.readingBooksSet = readingBooksSet;
    }
}
