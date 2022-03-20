package ru.otus.homework.dao.book;

import ru.otus.homework.domain.book.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();

    Book read(Long id);

    void delete(Long id);

    long create(Book book);

    void update(Book book);
}
