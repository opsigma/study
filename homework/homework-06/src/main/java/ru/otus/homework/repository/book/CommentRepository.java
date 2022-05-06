package ru.otus.homework.repository.book;

import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.book.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> getAll();

    Comment save(Comment comment);

    Comment getById(Long id);

    void deleteById(Long id);
}
