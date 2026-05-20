package org.example.springresapi.service;

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
    public Optional<PersonDTO> save(PersonDTO personDTO) {
        Person person = repository.save(new Person(personDTO.getName()));
        return Optional.of(new PersonDTO(person.getId(), person.getName()));
    }

    @Override
    public Optional<PersonDTO> findById(Long id) {
        return repository.findById(id)
                .map(person -> new PersonDTO(person.getId(), person.getName()));
    }

    @Override
    public Optional<PersonDTO> update(PersonDTO personDTO) {
        if (findById(personDTO.getId()).isEmpty()) {
            throw new RuntimeException();
        }
        Person person = repository.save(new Person(personDTO.getId(), personDTO.getName()));
        return Optional.of(new PersonDTO(person.getId(), person.getName()));
    }

    @Override
    public boolean deleteById(Long id) {
        if (findById(id).isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
