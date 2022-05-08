package ru.otus.homework.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.book.Comment;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment,Long> {

}
