package ru.otus.springdata.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.springdata.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Iterable<Person> findByName(String name);
}
