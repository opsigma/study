package ru.otus.homework.dao.author;

import ru.otus.homework.domain.author.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();

    Author read(long id);

    void delete(long id);

    long create(Author author);

    void update(Author author);
}
