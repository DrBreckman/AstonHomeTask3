package ru.sedov.task3.dto;


public class ReviewDto {

    private Integer mark;
    private String description;

    private String userName;
    private String bookName;

    public ReviewDto() {

    }

    public ReviewDto(Integer mark, String description) {

        this.mark = mark;
        this.description = description;
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

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getBookName() {

        return bookName;
    }

    public void setBookName(String bookName) {

        this.bookName = bookName;
    }
}
