package ru.otus.homework.repository.genre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.genre.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Репозиторий для работы с жанрами должен ")
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepositoryJpa genreRepository;

    @Autowired
    private TestEntityManager em;

    private Genre existingGenre1;
    private Genre existingGenre2;

    @BeforeEach
    void setUp() {
        existingGenre1 = Genre.builder().id(1L).name("genre1").build();
        existingGenre2 = Genre.builder().id(2L).name("genre2").build();
    }

    @Test
    @DisplayName("возвращать все жанры из БД")
    void shouldReturnAllGenres() {
        List<Genre> expectedGenres = List.of(existingGenre1, existingGenre2);
        List<Genre> actualGenres = genreRepository.getAll();
        assertThat(actualGenres).usingRecursiveComparison().isEqualTo(expectedGenres);
    }

    @Test
    @DisplayName("записывать новый жанр в БД")
    void createNewGenre() {

    }


    @Test
    @DisplayName("обновлять жанр в БД, если он там существует")
    void updateExistingGenre() {

    }

    @Test
    @DisplayName("удалять по ИД жанр из БД, если он там существует")
    void shouldCorrectDeleteGenreById() {

    }

}