package ru.otus.spring.rest;

import org.springframework.web.bind.annotation.*;
import ru.otus.spring.repostory.PersonRepository;
import ru.otus.spring.rest.dto.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/persons/all", method = RequestMethod.GET)
    public List<PersonDto> getAllPersons() {
        return repository.findAll().stream()
                .map(PersonDto::toDto)
                .collect(Collectors.toList());
    }
}
