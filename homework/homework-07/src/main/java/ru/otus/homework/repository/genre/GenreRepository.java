package ru.otus.homework.repository.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.domain.genre.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Long> {
    List<Genre> findByName(String name);
}
