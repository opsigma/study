package ru.otus.homework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.service.author.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @ShellMethod(value = "Get all author command", key = {"aa", "authorAll"})
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @ShellMethod(value = "Create author command", key = {"ac", "authorCreate"})
    public Author create(@ShellOption String name) {
        return authorService.create(name);
    }

    @ShellMethod(value = "Read author command", key = {"ar", "authorRead"})
    public Author read(@ShellOption Long id) {
        return authorService.read(id);
    }

    @ShellMethod(value = "Update author command", key = {"au", "authorUpdate"})
    public void update(@ShellOption String name, @ShellOption Long id) {
        authorService.update(id, name);
    }

    @ShellMethod(value = "Delete author command", key = {"ad", "authorDelete"})
    public void delete(@ShellOption Long id) {
        authorService.delete(id);
    }

}
