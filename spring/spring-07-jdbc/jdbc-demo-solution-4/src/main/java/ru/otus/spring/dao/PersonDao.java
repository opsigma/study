package ru.otus.spring.dao;

import ru.otus.spring.domain.Person;

public interface PersonDao {

    int count();

    void insert(Person person);
}
