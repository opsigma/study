package ru.otus.homework.repository.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.book.Comment;
import ru.otus.homework.domain.genre.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
@DisplayName("Репозиторий для работы с книгами должен ")
class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    private Book existingBook1;
    private Book existingBook2;

    private Author author1;
    private Genre genre1;

    @BeforeEach
    void setUp() {
        author1 = Author.builder().id(1L).name("author1").build();
        Author author2 = Author.builder().id(2L).name("author2").build();
        genre1 = Genre.builder().id(1L).name("genre1").build();

        existingBook1 = Book.builder().id(1L).name("book1")
                .author(author1)
                .genre(genre1)
                .build();
        existingBook2 = Book.builder().id(2L).name("book2")
                .author(author2)
                .genre(genre1)
                .build();

        Comment existingComment1 = Comment.builder().id(1L).book(existingBook1).comment("comment1_1").build();
        Comment existingComment2 = Comment.builder().id(2L).book(existingBook1).comment("comment1_2").build();
        Comment existingComment3 = Comment.builder().id(3L).book(existingBook1).comment("comment1_3").build();
        Comment existingComment4 = Comment.builder().id(4L).book(existingBook2).comment("comment2_1").build();

        existingBook1.setComments(List.of(existingComment1, existingComment2, existingComment3));
        existingBook2.setComments(List.of(existingComment4));
    }

    @Test
    @DisplayName("возвращать все книги из БД")
    void shouldReturnAllBooks() {
        List<Book> expectedBooks = List.of(existingBook1, existingBook2);
        List<Book> actualBooks = bookRepository.findAll();
        assertThat(actualBooks).usingRecursiveComparison().isEqualTo(expectedBooks);
    }

    @Test
    @DisplayName("возвращать все книги из БД по имени")
    void shouldReturnAllBooksByName() {
        List<Book> expectedBooks = List.of(existingBook1);
        List<Book> actualBooks = bookRepository.findByName(existingBook1.getName());
        assertThat(actualBooks).usingRecursiveComparison().isEqualTo(expectedBooks);
    }

    @Test
    @DisplayName("записывать новую книгу в БД")
    void createNewBook() {
        Book actualBook = bookRepository.save(Book.builder().name("new Book").author(author1).genre(genre1).build());
        Book expectedBook = Book.builder().id(3L).name("new Book").author(author1).genre(genre1).build();
        assertThat(actualBook).isEqualTo(expectedBook);
    }


    @Test
    @DisplayName("обновлять книгу в БД, если она там существует")
    void updateExistingBook() {
        existingBook1.setName("update name");
        Book actualBook = bookRepository.save(existingBook1);
        Book expectedBook = existingBook1;
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("удалять по ИД книгу из БД, если она там существует")
    void shouldCorrectDeleteAuthorById() {
        long id = 2L;
        bookRepository.deleteById(id);
        assertThatCode(() -> em.find(Book.class, id)).isNull();
    }
}