package ru.otus.homework.service.genre;

import ru.otus.homework.domain.genre.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Long create(String name);

    Genre read(Long id);

    void update(Long id, String name);

    void delete(Long id);
}
