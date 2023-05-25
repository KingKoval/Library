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
    private String author;

    @Min(value = 2, message = "Year should be greater than 2")
    private int year;


    private int person_id;


    public Book() {

    }
    public Book(int bookId, String title, String author, int year, int personId) {
        book_id = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
        person_id = personId;
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

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
