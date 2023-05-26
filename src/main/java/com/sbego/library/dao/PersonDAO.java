package com.sbego.library.dao;

import com.sbego.library.model.Book;
import com.sbego.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int person_id){
        return jdbcTemplate.query("SELECT * FROM person WHERE person_id=?", new Object[]{person_id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update(
                "INSERT INTO person(name, email, year_of_birth) values (?, ?, ?)",
                person.getName(),
                person.getEmail(),
                person.getYear_of_birth()
        );
    }

    public void update(int person_id, Person person) {
        jdbcTemplate.update(
                "UPDATE person SET name=?, email=?, year_of_birth=? WHERE person_id=?",
                person.getName(),
                person.getEmail(),
                person.getYear_of_birth(),
                person_id
        );
    }

    public void delete(int person_id){
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", person_id);
    }

    //check person in book
    public List<Book> checkBooks(int person_id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new Object[]{person_id},
                new BeanPropertyRowMapper<>(Book.class));
    }

}
