package ru.otus.homework.service.book;

import org.springframework.stereotype.Service;
import ru.otus.homework.domain.book.Comment;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> getAll() {
        return null;
    }

    @Override
    public Long create(Long bookId, String comment) {
        return null;
    }

    @Override
    public Comment read(Long id) {
        return null;
    }

    @Override
    public void update(Long bookId, String comment) {

    }

    @Override
    public void delete(Long id) {

    }
}
