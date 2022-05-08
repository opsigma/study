package ru.otus.homework.service.genre;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework.domain.genre.Genre;
import ru.otus.homework.repository.genre.GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис для работы с жанром должен")
@SpringBootTest
class GenreServiceImplTest {

    @Autowired
    private GenreServiceImpl genreService;

    @MockBean
    private GenreRepository genreRepository;

    private Genre existingGenre1;
    private Genre existingGenre2;

    @BeforeEach
    void setUp() {
        existingGenre1 = Genre.builder().id(1L).name("genre1").build();
        existingGenre2 = Genre.builder().id(2L).name("genre2").build();
    }

    @DisplayName("должен возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenreList() {
        List<Genre> expectedGenreList = List.of(existingGenre1, existingGenre2);
        Mockito.when(genreRepository.findAll()).thenReturn(expectedGenreList);
        List<Genre> actualBookList = genreRepository.findAll();
        assertThat(actualBookList).usingRecursiveComparison().isEqualTo(expectedGenreList);
    }

    @Test
    @DisplayName("создавать жанр")
    void shouldCreateBook() {
        var expectedGenre = Genre.builder().name("new Genre").build();
        genreService.create(expectedGenre.getName());
        Mockito.verify(genreRepository, Mockito.times(1)).save(expectedGenre);
    }

    @Test
    @DisplayName("возвращать жанр по идентификатору")
    void shouldReturnBookById() {
        var expectedGenre = existingGenre1;
        Mockito.when(genreRepository.getById(expectedGenre.getId())).thenReturn(expectedGenre);
        Genre actualBook = genreService.read(existingGenre1.getId());
        Assertions.assertThat(expectedGenre)
                .usingRecursiveComparison()
                .isEqualTo(actualBook);
    }

    @Test
    @DisplayName("обновлять жанр")
    void shouldUpdateBook() {
        var expectedGenre = existingGenre2;
        expectedGenre.setName("update Genre");
        genreService.update(expectedGenre.getId(), expectedGenre.getName());
        Mockito.verify(genreRepository, Mockito.times(1)).save(expectedGenre);
    }

    @Test
    @DisplayName("удалять жанр по его идентификатору")
    void shouldDeleteBookById() {
        var expectedGenre = existingGenre2;
        genreService.delete(expectedGenre.getId());
        Mockito.verify(genreRepository, Mockito.times(1)).deleteById(expectedGenre.getId());
    }
}