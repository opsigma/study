package ru.otus.homework.repository.book;

import ru.otus.homework.domain.book.Book;

import java.util.List;

public interface BookRepository {

    List<Book> getAll();

    List<Book> getAllByName(String name);

    Book save(Book book);

    Book getById(Long id);

    void deleteById(Long id);
}
