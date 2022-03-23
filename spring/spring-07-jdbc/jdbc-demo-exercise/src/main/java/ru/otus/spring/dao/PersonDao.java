package ru.otus.spring.dao;

import ru.otus.spring.domain.Person;

import java.util.List;

public interface PersonDao {

    int count();

    Person getById(long id);

    List<Person> getAll();

    void insert(Person person);

    void update(Person person);

    void deleteById(long id);

}
