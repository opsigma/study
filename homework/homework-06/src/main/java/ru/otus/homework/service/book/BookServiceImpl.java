package ru.otus.homework.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.book.Book;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Long create(String name, Long authorId, Long genreId) {
        return null;
    }

    @Override
    public Book read(Long id) {
        return null;
    }

    @Override
    public void update(Long id, String name, Long authorId, Long genreId) {

    }

    @Override
    public void delete(Long id) {

    }
}
