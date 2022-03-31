package ru.otus.homework.dao.book;

import ru.otus.homework.domain.book.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book getById(Long id);

    void deleteById(Long id);

    long insert(Book book);

    void update(Book book);
}
