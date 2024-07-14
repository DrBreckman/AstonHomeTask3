package ru.sedov.task3.dto;

import org.springframework.stereotype.Component;
import ru.sedov.task3.entity.Review;

import java.util.Set;

public class UserDto {

    private String name;
    private int age;
    private String address;

    private Set<ShortReviewDto> reviewSet;
    private Set<ShortBookDto> readingBooksSet;

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

    public Set<ShortReviewDto> getReviewSet() {

        return reviewSet;
    }

    public void setReviewSet(Set<ShortReviewDto> reviewSet) {

        this.reviewSet = reviewSet;
    }

    public Set<ShortBookDto> getReadingBooksSet() {

        return readingBooksSet;
    }

    public void setReadingBooksSet(Set<ShortBookDto> readingBooksSet) {

        this.readingBooksSet = readingBooksSet;
    }

    @Override
    public String toString() {

        return "UserDto{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", address='" + address + '\'' +
            ", reviewSet=" + reviewSet +
            ", readingBooksSet=" + readingBooksSet +
            '}';
    }
}
