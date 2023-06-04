package com.sbego.library.dao;

import com.sbego.library.model.Book;
import com.sbego.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        BeanPropertyRowMapper<Book> beanPropertyRowMapper = new BeanPropertyRowMapper<>(Book.class);
        beanPropertyRowMapper.setPrimitivesDefaultedForNullValue(true);

        return jdbcTemplate.query("SELECT * FROM book", beanPropertyRowMapper);
    }

    public Book show(int book_id) {
        BeanPropertyRowMapper<Book> beanPropertyRowMapper = new BeanPropertyRowMapper<>(Book.class);
        beanPropertyRowMapper.setPrimitivesDefaultedForNullValue(true);

        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{book_id},
                beanPropertyRowMapper).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update(
                "INSERT INTO book(title, author, year) VALUES (?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }

    public void update(int book_id, Book book) {
        jdbcTemplate.update(
                "UPDATE book SET title=?, author=?, year=? WHERE book_id=?",
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book_id
        );
    }

    public void delete(int book_id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", book_id);
    }

    public void makeReturn(int book_id) {
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE book_id=?", book_id);
    }

    public void makeReservation(int person_id, int book_id) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", person_id, book_id);
    }

}
