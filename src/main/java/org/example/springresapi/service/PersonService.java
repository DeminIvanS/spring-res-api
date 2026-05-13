package org.example.springresapi.service;

import org.example.springresapi.dto.PersonDTO;
import org.example.springresapi.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Optional<Person> findAll();

    PersonDTO save(PersonDTO personDTO);

    Optional<Person> findById(Long id);

    Optional<Person> update(Long id, PersonDTO personDTO);

    Optional<Person> deleteById(Long id);
}
