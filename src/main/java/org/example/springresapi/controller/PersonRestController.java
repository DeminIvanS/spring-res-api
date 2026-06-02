package org.example.springresapi.controller;

import jakarta.validation.Valid;
import org.example.springresapi.dto.PersonDTO;
import org.example.springresapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PersonRestController {

    private final PersonService service;

    public PersonRestController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public List<PersonDTO> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PersonDTO view(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO newPerson) {
        PersonDTO savedPerson = service.save(newPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id,
                                            @Valid @RequestBody PersonDTO personDTO) {
        personDTO.setId(id);
        PersonDTO updatePerson = service.update(personDTO);
        return ResponseEntity.ok(updatePerson);

    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}


