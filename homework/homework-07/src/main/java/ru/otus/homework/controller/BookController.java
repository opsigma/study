package ru.otus.homework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.service.book.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @ShellMethod(value = "Get all book command", key = {"ba", "bookAll"})
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Create book command", key = {"bc", "bookCreate"})
    public Long create(@ShellOption String name, @ShellOption Long authorId, @ShellOption Long genreId) {
        return bookService.create(name, authorId, genreId);
    }

    @ShellMethod(value = "Read book command", key = {"br", "bookRead"})
    public Book read(@ShellOption Long id) {
        return bookService.read(id);
    }

    @ShellMethod(value = "Update book command", key = {"bu", "bookUpdate"})
    public void update(@ShellOption String name, @ShellOption Long authorId, @ShellOption Long genreId, @ShellOption Long id) {
        bookService.update(id, name, authorId, genreId);
    }

    @ShellMethod(value = "Delete book command", key = {"bd", "bookDelete"})
    public void delete(@ShellOption Long id) {
        bookService.delete(id);
    }

}
