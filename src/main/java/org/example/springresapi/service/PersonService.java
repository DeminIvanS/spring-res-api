package org.example.springresapi.service;

import org.example.springresapi.dto.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<PersonDTO> findAll();

    PersonDTO save(PersonDTO personDTO);

    PersonDTO findById(Long id);

    PersonDTO update(PersonDTO personDTO);

    void deleteById(Long id);
}
