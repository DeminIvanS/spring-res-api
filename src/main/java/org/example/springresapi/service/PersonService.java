package org.example.springresapi.service;

import org.example.springresapi.dto.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<PersonDTO> findAll();

    PersonDTO save(PersonDTO personDTO);

    Optional<PersonDTO> findById(Long id);

    Optional<PersonDTO> update(PersonDTO personDTO);

    boolean deleteById(Long id);
}
