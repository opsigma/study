package ru.otus.homework.service.author;

import ru.otus.homework.domain.author.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Long create(String name);

    Author read(long id);

    void update(long id, String authorName);

    void delete(long id);

}
