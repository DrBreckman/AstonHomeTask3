package ru.sedov.task3.dto;

import org.springframework.stereotype.Component;
import ru.sedov.task3.entity.User;

@Component
public class UserDto {
    private String name;
    private int age;
    private String address;

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
}
