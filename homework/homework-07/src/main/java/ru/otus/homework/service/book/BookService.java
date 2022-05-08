package ru.otus.homework.service.book;

import ru.otus.homework.domain.book.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    List<Book> getAllByName(String name);

    Book create(String name, Long authorId, Long genreId);

    Book read(Long id);

    void update(Long id, String name, Long authorId, Long genreId);

    void delete(Long id);
}
