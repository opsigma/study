package ru.otus.homework.repository.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.book.Comment;
import ru.otus.homework.domain.genre.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Репозиторий для работы с книгами должен ")
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    private Book existingBook1;
    private Book existingBook2;

    @BeforeEach
    void setUp() {
        Author author1 = Author.builder().id(1L).name("author1").build();
        Author author2 = Author.builder().id(2L).name("author2").build();
        Genre genre1 = Genre.builder().id(1L).name("genre1").build();

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
    @DisplayName("возвращать всех книги из БД")
    void shouldReturnAllBooks() {
        List<Book> expectedBooks = List.of(existingBook1, existingBook2);
        List<Book> actualBooks = bookRepositoryJpa.getAll();
        assertThat(actualBooks).usingRecursiveComparison().isEqualTo(expectedBooks);
    }
}