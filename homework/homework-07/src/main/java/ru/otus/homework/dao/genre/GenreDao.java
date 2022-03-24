package ru.otus.homework.dao.genre;

import ru.otus.homework.domain.genre.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    Genre getById(Long id);

    void deleteById(Long id);

    long insert(Genre genre);

    void update(Genre genre);

}
