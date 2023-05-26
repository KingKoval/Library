package com.sbego.library.controller;

import com.sbego.library.dao.PersonDAO;
import com.sbego.library.model.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int person_id, Model model) {
        model.addAttribute("person", personDAO.show(person_id));
        model.addAttribute("books", personDAO.checkBooks(person_id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());

        return "people/new";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "people/new";
        }

        personDAO.save(person);

        return "redirect:/people";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int person_id) {
        personDAO.delete(person_id);

        return "redirect:/people";
    }

}
