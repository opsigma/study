package ru.otus.homework.dao.author;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework.domain.author.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@JdbcTest
@Import(AuthorDaoJdbc.class)
@DisplayName("Dao для работы с авторами должно")
class AuthorDaoJdbcTest {

    private static final Long EXISTING_AUTHOR_ID = 2L;
    private static final String EXISTING_AUTHOR_NAME = "author2";
    
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    @DisplayName("должен возвращать ожидаемый список авторов.")
    void shouldReturnExpectedAuthorList() {
        var expectedAuthor1 = Author.builder().id(1L).name("author").build();
        var expectedAuthor2 = Author.builder().id(EXISTING_AUTHOR_ID).name(EXISTING_AUTHOR_NAME).build();
        List<Author> actualAuthorList = authorDaoJdbc.getAll();
        assertThat(actualAuthorList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedAuthor1, expectedAuthor2);
    }

    @Test
    @DisplayName("добавлять автора в БД")
    void shouldInsertAuthor() {
        var expectedAuthor = Author.builder().id(3L).name("Тестовый автор").build();
        authorDaoJdbc.insert(expectedAuthor);
        var actualAuthor = authorDaoJdbc.getById(3L);
        Assertions.assertThat(expectedAuthor)
                .usingRecursiveComparison()
                .isEqualTo(actualAuthor);
    }

    @Test
    @DisplayName("возвращать автора в БД")
    void shouldReturnAuthorById() {
        var expectedAuthor = authorDaoJdbc.getById(EXISTING_AUTHOR_ID);
        var actualAuthor = Author.builder().id(EXISTING_AUTHOR_ID).name(EXISTING_AUTHOR_NAME).build();
        Assertions.assertThat(expectedAuthor)
                .usingRecursiveComparison()
                .isEqualTo(actualAuthor);
    }

    @Test
    @DisplayName("обновлять автора в БД")
    void shouldUpdateAuthor() {
        var expectedAuthor = Author.builder().id(2L).name("author2 update").build();
        authorDaoJdbc.update(expectedAuthor);
        var actualAuthor = authorDaoJdbc.getById(2L);
        Assertions.assertThat(expectedAuthor)
                .usingRecursiveComparison()
                .isEqualTo(actualAuthor);
    }

    @Test
    @DisplayName("удалять автора по его идентификатору")
    void shouldDeleteAuthorById() {
        assertThatCode(() -> authorDaoJdbc.getById(EXISTING_AUTHOR_ID))
                .doesNotThrowAnyException();

        authorDaoJdbc.deleteById(EXISTING_AUTHOR_ID);

        assertThatThrownBy(() -> authorDaoJdbc.getById(EXISTING_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}