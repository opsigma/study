package ru.otus.homework.repository.genre;

import ru.otus.homework.domain.genre.Genre;

import java.util.List;

public interface GenreRepository {

    List<Genre> getAll();

    Genre save(Genre genre);

    Genre getById(Long id);

    void deleteById(Long id);
}
