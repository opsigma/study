package ru.otus.homework.dao.genre;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework.domain.genre.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@Import(GenreDaoJdbc.class)
@DisplayName("Dao для работы с жанрами должно")
class GenreDaoJdbcTest {

    private static final Long EXISTING_GENRE_ID = 2L;
    private static final String EXISTING_GENRE_NAME = "genre2";
    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @Test
    @DisplayName("возвращать ожидаемый список жанров.")
    void shouldReturnExpectedGenreList() {
        var expectedGenre1 = Genre.builder().id(1L).name("genre").build();
        var expectedGenre2 = Genre.builder().id(EXISTING_GENRE_ID).name(EXISTING_GENRE_NAME).build();
        List<Genre> actualGenreList = genreDaoJdbc.getAll();
        assertThat(actualGenreList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedGenre1, expectedGenre2);
    }

    @Test
    @DisplayName("добавлять жанр в БД")
    void shouldInsertAuthor() {
        var expectedGenre = Genre.builder().id(3L).name("Тестовый жанр").build();
        genreDaoJdbc.insert(expectedGenre);
        var actualGenre = genreDaoJdbc.getById(3L);
        Assertions.assertThat(expectedGenre)
                .usingRecursiveComparison()
                .isEqualTo(actualGenre);
    }

    @Test
    @DisplayName("возвращать жанр в БД")
    void shouldReturnGenreById() {
        var expectedGenre = genreDaoJdbc.getById(EXISTING_GENRE_ID);
        var actualGenre = Genre.builder().id(EXISTING_GENRE_ID).name(EXISTING_GENRE_NAME).build();
        Assertions.assertThat(expectedGenre)
                .usingRecursiveComparison()
                .isEqualTo(actualGenre);
    }

    @Test
    @DisplayName("обновлять жанр в БД")
    void shouldUpdateGenre() {
        Genre expectedGenre = Genre.builder().id(2L).name("genre2 update").build();
        genreDaoJdbc.update(expectedGenre);
        var actualGenre = genreDaoJdbc.getById(2L);
        Assertions.assertThat(expectedGenre)
                .usingRecursiveComparison()
                .isEqualTo(actualGenre);
    }

    @Test
    @DisplayName("удалять жанр по его идентификатору")
    void shouldDeleteGenreById() {
        assertThatCode(() -> genreDaoJdbc.getById(EXISTING_GENRE_ID))
                .doesNotThrowAnyException();

        genreDaoJdbc.deleteById(EXISTING_GENRE_ID);

        assertThatThrownBy(() -> genreDaoJdbc.getById(EXISTING_GENRE_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}