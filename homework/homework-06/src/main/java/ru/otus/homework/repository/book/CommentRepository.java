package ru.otus.homework.repository.book;

import org.springframework.lang.NonNull;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.book.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> getAll();
    List<Comment> getAllByBook(Book book);

    Comment save(Comment comment);

    Comment getById(Long id);

    void deleteById(Long id);
}
