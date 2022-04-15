package ru.otus.homework.service.book;

import ru.otus.homework.domain.book.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAll();

    Long create(Long bookId, String comment);

    Comment read(Long id);

    void update(Long bookId, String comment);

    void delete(Long id);
}
