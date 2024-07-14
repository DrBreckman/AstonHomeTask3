package ru.sedov.task3.dto;

public class ShortReviewDto {

    private Integer mark;
    private String description;

    public ShortReviewDto() {

    }

    public ShortReviewDto(Integer mark, String description) {

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

    @Override
    public String toString() {

        return "ShortReviewDto{" +
            "mark=" + mark +
            ", description='" + description + '\'' +
            '}';
    }
}
