package ru.otus.springdata.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.springdata.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByName(String name);

    @Override
    List<Person> findAll();

    Optional<Person> findByEmailAddress(String address);
}
