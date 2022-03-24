package ru.otus.homework.dao.author;

import ru.otus.homework.domain.author.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> getAll();

    Author getById(long id);

    void deleteById(long id);

    long insert(Author author);

    void update(Author author);
}
