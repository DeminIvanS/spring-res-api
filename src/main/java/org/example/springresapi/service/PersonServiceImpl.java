package org.example.springresapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.springresapi.dto.PersonDTO;
import org.example.springresapi.entity.Person;
import org.example.springresapi.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PersonDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(person -> new PersonDTO(person.getId(), person.getName()))
                .toList();
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Person person = repository.save(new Person(personDTO.getName()));
        return new PersonDTO(person.getId(), person.getName());
    }

    @Override
    public PersonDTO findById(Long id) {
        return repository.findById(id)
                .map(person -> new PersonDTO(person.getId(), person.getName()))
                .orElseThrow(()-> new EntityNotFoundException("Person not found with id: " + id));
    }

    @Override
    public PersonDTO update(PersonDTO personDTO) {
        if (!repository.existsById(personDTO.getId())) {
            throw new EntityNotFoundException("Person not found with id: " + personDTO.getId());
        }
        Person person = repository.save(new Person(personDTO.getId(), personDTO.getName()));
        return new PersonDTO(person.getId(), person.getName());
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Person not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
