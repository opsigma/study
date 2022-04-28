package ru.otus.homework.repository.book;

import ru.otus.homework.domain.book.Book;

import java.util.List;

public interface BookRepository {

    List<Book> getAll();

    Book save(Book book);

    Book getById(Long id);

    void update(Long id, String name, Long authorId, Long genreId);

    void deleteById(Long id);
}
