package ru.otus.homework.repository.genre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework.domain.genre.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
@DisplayName("Репозиторий для работы с жанрами должен ")
class GenreRepositoryJpaTest {

    @Autowired
    private GenreRepository genreRepository;

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
        List<Genre> actualGenres = genreRepository.findAll();
        assertThat(actualGenres).usingRecursiveComparison().isEqualTo(expectedGenres);
    }

    @Test
    @DisplayName("возвращать все жанры из БД по имени")
    void shouldReturnAllGenresByName() {
        List<Genre> expectedGenres = List.of(existingGenre1);
        List<Genre> actualGenres = genreRepository.findByName(existingGenre1.getName());
        assertThat(actualGenres).usingRecursiveComparison().isEqualTo(expectedGenres);
    }

    @Test
    @DisplayName("записывать новый жанр в БД")
    void createNewGenre() {
        Genre actualGenre = genreRepository.save(Genre.builder().name("new Genre").build());
        Genre expectedGenre = Genre.builder().id(3L).name("new Genre").build();
        assertThat(actualGenre).isEqualTo(expectedGenre);
    }


    @Test
    @DisplayName("обновлять жанр в БД, если он там существует")
    void updateExistingGenre() {
        existingGenre1.setName("new Genre");
        Genre expectedGenre = genreRepository.save(existingGenre1);
        Genre actualGenre = em.find(Genre.class, existingGenre1.getId());
        assertThat(actualGenre).isEqualTo(expectedGenre);
    }

    @Test
    @DisplayName("удалять по ИД жанр из БД, если он там существует")
    void shouldCorrectDeleteGenreById() {
        long id = 2L;
        genreRepository.deleteById(id);
        assertThatCode(() -> em.find(Genre.class, id)).isNull();
    }

}