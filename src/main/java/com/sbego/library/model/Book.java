package com.sbego.library.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {
    private int book_id;

    @NotEmpty(message = "Title of book should not be empty")
    @Size(min = 1, max = 100, message = "Title of book should be between 1 and 100 characters")
    private String title;

    @NotEmpty(message = "Author should not be empty ")
    @Size(min = 2, message = "Author should be minimum 2 characters")
    private String author;

    @Min(value = 1800, message = "Year should be greater than 1800")
    private int year;


    private Integer person_id;

    public Book() {

    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year + '\'' +
                ", person_id='" + person_id +
                '}';
    }
}
