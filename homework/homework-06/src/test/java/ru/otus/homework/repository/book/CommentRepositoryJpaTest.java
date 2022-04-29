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
import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
@DisplayName("Репозиторий для работы с комментариями должен ")
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {


    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    private Comment existingComment1;
    private Comment existingComment2;
    private Comment existingComment3;
    private Comment existingComment4;

    private Book existingBook1;

    @BeforeEach
    void setUp() {
        Author author1 = Author.builder().id(1L).name("author1").build();
        Author author2 = Author.builder().id(2L).name("author2").build();
        Genre genre1 = Genre.builder().id(1L).name("genre1").build();
        existingBook1 = Book.builder().id(1L).name("book1")
                .author(author1)
                .genre(genre1)
                .build();
        Book book2 = Book.builder().id(2L).name("book2")
                .author(author2)
                .genre(genre1)
                .build();
        existingComment1 = Comment.builder().id(1L).book(existingBook1).comment("comment1_1").build();
        existingComment2 = Comment.builder().id(2L).book(existingBook1).comment("comment1_2").build();
        existingComment3 = Comment.builder().id(3L).book(existingBook1).comment("comment1_3").build();
        existingComment4 = Comment.builder().id(4L).book(book2).comment("comment2_1").build();
        existingBook1.setComments(List.of(existingComment1, existingComment2, existingComment3));
        book2.setComments(List.of(existingComment4));

    }

    @DisplayName("возвращать все комментарии из БД")
    @Test
    void shouldReturnAllComments() {
        List<Comment> expectedComments = List.of(existingComment1, existingComment2, existingComment3, existingComment4);
        List<Comment> actualComments = commentRepositoryJpa.getAll();
        assertThat(actualComments).isEqualTo(expectedComments);
    }

    @DisplayName("возвращать все комментарии из БД по книге")
    @Test
    void shouldReturnAllCommentsByBook() {
        List<Comment> expectedComments = List.of(existingComment1, existingComment2, existingComment3);
        List<Comment> actualComments = commentRepositoryJpa.getAllByBook(existingBook1);
        assertThat(actualComments).isEqualTo(expectedComments);
    }

    @Test
    @DisplayName("записывать новый комментарий в БД")
    void createNewComment() {
        Comment actualComment = commentRepositoryJpa.save(Comment.builder().book(existingBook1).comment("new Comment").build());
        Comment expectedComment = Comment.builder().id(5L).book(existingBook1).comment("new Comment").build();
        assertThat(actualComment).isEqualTo(expectedComment);
    }


    @Test
    @DisplayName("обновлять комментарий в БД, если он там существует")
    void updateExistingComment() {
        existingComment1.setComment("new text");
        Comment expectedComment = commentRepositoryJpa.save(existingComment1);
        Comment actualComment =  em.find(Comment.class, existingComment1.getId());
        assertThat(actualComment).isEqualTo(expectedComment);
    }

    @Test
    @DisplayName("удалять по ИД комментарий из БД, если он там существует")
    void shouldCorrectDeleteCommentById() {
        long id = 2L;
        commentRepositoryJpa.deleteById(id);
        assertThatCode(() -> em.find(Comment.class, id)).isNull();
    }
}