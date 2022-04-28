package ru.otus.homework.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.genre.Genre;
import ru.otus.homework.repository.book.BookRepository;
import ru.otus.homework.repository.book.BookRepositoryJpa;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    @Transactional
    public Long create(String name, Long authorId, Long genreId) {
        Book book = Book.builder().name(name)
                .author(Author.builder().id(authorId).build())
                .genre(Genre.builder().id(genreId).build())
                .build();
        return bookRepository.save(book).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Book read(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String name, Long authorId, Long genreId) {
        bookRepository.update(id,name,authorId,genreId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
