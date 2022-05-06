package ru.otus.homework.repository.author;

import ru.otus.homework.domain.author.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> getAll();

    List<Author> getByName(String name);

    Author save(Author author);

    Author getById(long id);

    void deleteById(long id);

}
