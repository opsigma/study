package ru.otus.homework.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.book.Comment;
import ru.otus.homework.repository.book.CommentRepository;
import ru.otus.homework.repository.book.CommentRepositoryJpa;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepositoryJpa commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAll() {
        return commentRepository.getAll();
    }

    @Override
    @Transactional
    public Long create(Long bookId, String comment) {
        Comment  c = Comment.builder()
                .book(Book.builder().id(bookId).build())
                .comment(comment)
                .build();
        return commentRepository.save(c).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Comment read(Long id) {
        return commentRepository.getById(id);
    }

    @Override
    @Transactional
    public void update(Long id,Long bookId, String comment) {
        Comment  c = Comment.builder()
                .id(id)
                .book(Book.builder().id(bookId).build())
                .comment(comment)
                .build();
        commentRepository.save(c);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
