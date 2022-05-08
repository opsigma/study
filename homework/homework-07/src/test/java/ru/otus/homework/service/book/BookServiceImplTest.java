package ru.otus.homework.service.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.genre.Genre;
import ru.otus.homework.repository.book.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис для работы с книгами должно")
@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private BookRepository bookRepository;

    private Author existingAuthor1;
    private Author existingAuthor2;
    private Genre existingGenre1;
    private Genre existingGenre2;
    private Book existingBook1;
    private Book existingBook2;

    @BeforeEach
    void setUp() {
        existingAuthor1 = Author.builder().id(1L).build();
        existingAuthor2 = Author.builder().id(2L).build();
        existingGenre1 = Genre.builder().id(1L).build();
        existingGenre2 = Genre.builder().id(2L).build();
        existingBook1 = Book.builder().id(1L).name("book1").author(existingAuthor1).genre(existingGenre1).build();
        existingBook2 = Book.builder().id(2L).name("book2").author(existingAuthor2).genre(existingGenre1).build();
    }


    @DisplayName("должен возвращать ожидаемый список книг.")
    @Test
    void shouldReturnExpectedBookList() {
        List<Book> expectedBookList = List.of(existingBook1, existingBook2);
        Mockito.when(bookRepository.findAll()).thenReturn(expectedBookList);
        List<Book> actualBookList = bookRepository.findAll();
        assertThat(actualBookList).usingRecursiveComparison().isEqualTo(expectedBookList);
    }

    @Test
    @DisplayName("создавать книгу")
    void shouldCreateBook() {
        var expectedBook = Book.builder().name("Тестовая книга")
                .author(existingAuthor1)
                .genre(existingGenre1).build();
        bookService.create(expectedBook.getName(), expectedBook.getAuthor().getId(), expectedBook.getGenre().getId());
        Mockito.verify(bookRepository, Mockito.times(1)).save(expectedBook);
    }

    @Test
    @DisplayName("возвращать книгу по идентификатору")
    void shouldReturnBookById() {
        var expectedBook = existingBook1;
        Mockito.when(bookRepository.getById(expectedBook.getId())).thenReturn(expectedBook);
        Book actualBook = bookService.read(existingBook1.getId());
        Assertions.assertThat(expectedBook)
                .usingRecursiveComparison()
                .isEqualTo(actualBook);
    }

    @Test
    @DisplayName("обновлять книгу")
    void shouldUpdateBook() {
        var expectedBook = existingBook2;
        expectedBook.setAuthor(existingAuthor1);
        expectedBook.setGenre(existingGenre1);
        bookService.update(expectedBook.getId(), expectedBook.getName(), expectedBook.getAuthor().getId(), expectedBook.getGenre().getId());
        Mockito.verify(bookRepository, Mockito.times(1)).save(expectedBook);
    }

    @Test
    @DisplayName("удалять книгу по его идентификатору")
    void shouldDeleteBookById() {
        var expectedBook = existingBook2;
        bookService.delete(expectedBook.getId());
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(expectedBook.getId());
    }
}