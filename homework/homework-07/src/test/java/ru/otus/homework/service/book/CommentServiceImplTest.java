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
import ru.otus.homework.domain.book.Comment;
import ru.otus.homework.domain.genre.Genre;
import ru.otus.homework.repository.book.CommentRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис для работы с комментариями должен")
@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @MockBean
    private CommentRepository commentRepository;

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

    @DisplayName("должен возвращать ожидаемый список комментариевов")
    @Test
    void shouldReturnExpectedGenreList() {
        List<Comment> expectedCommentList = List.of(existingComment1, existingComment2, existingComment3, existingComment4);
        Mockito.when(commentRepository.findAll()).thenReturn(expectedCommentList);
        List<Comment> actualCommentList = commentRepository.findAll();
        assertThat(actualCommentList).usingRecursiveComparison().isEqualTo(expectedCommentList);
    }

    @Test
    @DisplayName("создавать комментарий")
    void shouldCreateComment() {
        var expectedComment = Comment.builder().book(existingBook1).comment("new Comment").build();
        Comment actualComment = commentService.create(existingBook1.getId(), expectedComment.getComment());
        Mockito.verify(commentRepository, Mockito.times(1)).save(expectedComment);
        Mockito.when(commentRepository.save(expectedComment)).thenReturn(actualComment);
    }

    @Test
    @DisplayName("возвращать комментарий по идентификатору")
    void shouldReturnCommentById() {
        var expectedComment = existingComment1;
        Mockito.when(commentRepository.getById(expectedComment.getId())).thenReturn(expectedComment);
        Comment actualComment = commentService.read(existingComment1.getId());
        Assertions.assertThat(expectedComment)
                .usingRecursiveComparison()
                .isEqualTo(actualComment);
    }

    @Test
    @DisplayName("обновлять комментарий")
    void shouldUpdateComment() {
        var expectedComment = existingComment2;
        expectedComment.setComment("update Comment");
        commentService.update(expectedComment.getId(), expectedComment.getBook().getId(), expectedComment.getComment());
        Mockito.verify(commentRepository, Mockito.times(1)).save(expectedComment);
    }

    @Test
    @DisplayName("удалять комментарий по его идентификатору")
    void shouldDeleteCommentById() {
        var expectedComment = existingComment2;
        commentService.delete(expectedComment.getId());
        Mockito.verify(commentRepository, Mockito.times(1)).deleteById(expectedComment.getId());
    }
}