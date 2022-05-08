package ru.otus.homework.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.book.Comment;
import ru.otus.homework.repository.book.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookService bookService;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getAllByBook(Long bookId) {
        Book book = bookService.read(bookId);
        return Optional.ofNullable(book).map(Book::getComments).orElse(new ArrayList<>());
    }

    @Override
    @Transactional
    public Comment create(Long bookId, String comment) {
        Book book = bookService.read(bookId);
        Comment c = Comment.builder()
                .book(book)
                .comment(comment)
                .build();
        return commentRepository.save(c);
    }

    @Override
    public Comment read(Long id) {
        return commentRepository.getById(id);
    }

    @Override
    @Transactional
    public void update(Long id, Long bookId, String comment) {
        Book book = bookService.read(bookId);
        Comment c = Comment.builder()
                .id(id)
                .book(book)
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
