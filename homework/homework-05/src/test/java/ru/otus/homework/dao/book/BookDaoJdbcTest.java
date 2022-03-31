package ru.otus.homework.dao.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.genre.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
@Import(BookDaoJdbc.class)
@DisplayName("Dao для работы с книгами должно")
class BookDaoJdbcTest {

    private Author existingAuthor1;
    private Author existingAuthor2;
    private Genre existingGenre1;
    private Genre existingGenre2;
    private Book existingBook1;
    private Book existingBook2;

    @BeforeEach
    void setUp() {
        existingAuthor1 = Author.builder().id(1L).name("author").build();
        existingAuthor2 = Author.builder().id(2L).name("author2").build();
        existingGenre1 = Genre.builder().id(1L).name("genre").build();
        existingGenre2 = Genre.builder().id(2L).name("genre2").build();
        existingBook1 = Book.builder().id(1L).name("book").author(existingAuthor1).genre(existingGenre1).build();
        existingBook2 = Book.builder().id(2L).name("book2").author(existingAuthor2).genre(existingGenre2).build();
    }

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    @DisplayName("должен возвращать ожидаемый список книг.")
    void shouldReturnExpectedBookList() {
        List<Book> actualBookList = bookDaoJdbc.getAll();
        assertThat(actualBookList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(existingBook1, existingBook2);
    }

    @Test
    @DisplayName("добавлять книгу в БД")
    void shouldInsertBook() {
        var expectedBook = Book.builder().id(3L).name("Тестовая книга").author(existingAuthor1).genre(existingGenre1).build();
        bookDaoJdbc.insert(expectedBook);
        var actualBook = bookDaoJdbc.getById(3L);
        Assertions.assertThat(expectedBook)
                .usingRecursiveComparison()
                .isEqualTo(actualBook);
    }

    @Test
    @DisplayName("возвращать книгу в БД")
    void shouldReturnBookById() {
        var expectedBook = bookDaoJdbc.getById(existingBook1.getId());
        var actualBook = existingBook1;
        Assertions.assertThat(expectedBook)
                .usingRecursiveComparison()
                .isEqualTo(actualBook);
    }

    @Test
    @DisplayName("обновлять книгу в БД")
    void shouldUpdateBook() {
        var expectedBook = existingBook2;
        expectedBook.setAuthor(existingAuthor1);
        expectedBook.setGenre(existingGenre1);
        bookDaoJdbc.update(expectedBook);
        var actualBook = bookDaoJdbc.getById(expectedBook.getId());
        Assertions.assertThat(expectedBook)
                .usingRecursiveComparison()
                .isEqualTo(actualBook);
    }

    @Test
    @DisplayName("удалять кингу по его идентификатору")
    void shouldDeleteBookById() {
        assertThatCode(() -> bookDaoJdbc.getById(existingBook2.getId()))
                .doesNotThrowAnyException();

        bookDaoJdbc.deleteById(existingBook2.getId());

        assertThatThrownBy(() -> bookDaoJdbc.getById(existingBook2.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}