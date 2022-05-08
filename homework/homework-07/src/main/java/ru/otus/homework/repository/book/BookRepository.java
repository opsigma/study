package ru.otus.homework.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.domain.book.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByName(String name);
}
