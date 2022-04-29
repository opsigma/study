package ru.otus.homework.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.genre.Genre;
import ru.otus.homework.repository.book.BookRepositoryJpa;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa bookRepositoryJpa;

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepositoryJpa.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllByName(String name) {
        return bookRepositoryJpa.getAllByName(name);
    }

    @Override
    @Transactional
    public Book create(String name, Long authorId, Long genreId) {
        Book book = Book.builder().name(name)
                .author(Author.builder().id(authorId).build())
                .genre(Genre.builder().id(genreId).build())
                .build();
        return bookRepositoryJpa.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book read(Long id) {
        return bookRepositoryJpa.getById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String name, Long authorId, Long genreId) {
        Book book = Book.builder().name(name)
                .id(id)
                .author(Author.builder().id(authorId).build())
                .genre(Genre.builder().id(genreId).build())
                .build();
        bookRepositoryJpa.save(book);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepositoryJpa.deleteById(id);
    }
}
