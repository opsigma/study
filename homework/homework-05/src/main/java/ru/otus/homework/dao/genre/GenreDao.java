package ru.otus.homework.dao.genre;

import ru.otus.homework.domain.genre.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();

    Genre read(Long id);

    void delete(Long id);

    long create(Genre genre);

    void update(Genre genre);
}
