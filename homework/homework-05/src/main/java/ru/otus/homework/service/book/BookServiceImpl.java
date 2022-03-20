package ru.otus.homework.service.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.author.AuthorDao;
import ru.otus.homework.dao.book.BookDao;
import ru.otus.homework.dao.genre.GenreDao;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.genre.Genre;
import ru.otus.homework.exeption.AppException;
import ru.otus.homework.service.locale.LocaleService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    public static final String S_AUTHOR_NOT_FOUND = "strings.app.notFound.author";
    public static final String S_GENRE_NOT_FOUND = "strings.app.notFound.genre";
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final LocaleService localeService;


    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Long create(String name, Long authorId, Long genreId) {
        Author author = getAuthor(authorId);
        Genre genre = getGenre(genreId);
        Book book = Book.builder().name(name).author(author).genre(genre).build();
        return bookDao.create(book);
    }

    @Override
    public Book read(Long id) {
        return bookDao.read(id);
    }

    @Override
    public void update(Long id, String name, Long authorId, Long genreId) {
        Author author = getAuthor(authorId);
        Genre genre = getGenre(genreId);
        Book book = Book.builder()
                .id(id)
                .name(name)
                .author(author)
                .genre(genre)
                .build();
        bookDao.update(book);
    }

    @Override
    public void delete(Long id) {
        bookDao.delete(id);
    }

    private Author getAuthor(Long authorId) {
        return Optional.ofNullable(authorId)
                .map(authorDao::read)
                .orElseThrow(() -> new AppException(localeService.getFormatMessage(S_AUTHOR_NOT_FOUND, authorId)));
    }

    private Genre getGenre(Long genreId) {
        return Optional.ofNullable(genreId)
                .map(genreDao::read)
                .orElseThrow(() -> new AppException(localeService.getFormatMessage(S_GENRE_NOT_FOUND, genreId)));
    }
}
