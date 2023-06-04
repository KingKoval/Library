package com.sbego.library.controller;

import com.sbego.library.dao.BookDAO;
import com.sbego.library.dao.PersonDAO;
import com.sbego.library.model.Book;
import com.sbego.library.model.Person;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private static final Logger LOG = LoggerFactory.getLogger(BooksController.class);

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        System.out.println("console robe");
        model.addAttribute("books", bookDAO.index());

        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int book_id, Model model,
                       @ModelAttribute("person") Person person) {
        Book book = bookDAO.show(book_id);

        model.addAttribute("book", book);

        if(book.getPerson_id() != null)
            model.addAttribute("person", personDAO.show(book.getPerson_id()));
        else
            model.addAttribute("people", personDAO.index());

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());

        return "books/new";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "books/new";
        }

        bookDAO.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int book_id, Model model) {
        model.addAttribute("book", bookDAO.show(book_id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int book_id,
                             @ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDAO.update(book_id, book);

        return "redirect:/books";
    }

    @PatchMapping("/{id}/return")
    public String bookReturn(@PathVariable("id") int book_id) {
        bookDAO.makeReturn(book_id);

        return "redirect:/books/" + book_id;
    }

    @PatchMapping("/{id}/reservation")
    public String bookReservation(@PathVariable("id") int book_id,
                                  @ModelAttribute("person") Person person){
        if(person == null)
            System.out.println("person is null");
        else
            System.out.println(person);

        bookDAO.makeReservation(person.getPerson_id(), book_id);


        return "redirect:/books/" + book_id;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int book_id) {
        bookDAO.delete(book_id);

        return "redirect:/books";
    }
}
