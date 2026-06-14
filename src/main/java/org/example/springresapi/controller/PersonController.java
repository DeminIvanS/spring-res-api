package org.example.springresapi.controller;

import jakarta.validation.Valid;
import org.example.springresapi.dto.PersonDTO;
import org.example.springresapi.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("persons", service.findAll());
        return "persons/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        PersonDTO person = service.findById(id);
        model.addAttribute("person", person);
        return "persons/view";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("person", new PersonDTO());
        return "persons/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute PersonDTO person,
                         BindingResult result) {
        if (result.hasErrors()) return "persons/form";
        service.save(person);
        return "redirect:/persons";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        PersonDTO person = service.findById(id);
        model.addAttribute("person", person);
        return "persons/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute PersonDTO person,
                         BindingResult result) {
        if (result.hasErrors()) return "persons/form";
        person.setId(id);
        service.save(person);
        return "redirect:/persons/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/persons";
    }
}


