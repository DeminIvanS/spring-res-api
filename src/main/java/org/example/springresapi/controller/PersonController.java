package org.example.springresapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.springresapi.entity.Person;
import org.example.springresapi.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
@AllArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("persons", service.findAll());
        return "persons/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Person person = service.findById(id)
                .orElse(null);
        if (person == null) return "redirect:/persons";
        model.addAttribute("person", person);
        return "persons/view";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("person", new Person());
        return "persons/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Person person,
                         BindingResult result) {
        if (result.hasErrors()) return "persons/form";
        service.save(person);
        return "redirect:/persons";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Person person = service.findById(id)
                .orElse(null);
        if (person == null) return "redirect:/persons";
        model.addAttribute("person", person);
        return "persons/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute Person person,
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

  /*  private ResponseEntity<PersonDTO> mappingResponsePerson(PersonDTO person) {
        return new ResponseEntity<>(person, HttpStatus.OK);
    }*/

