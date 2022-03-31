package ru.otus.homework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.genre.Genre;
import ru.otus.homework.service.genre.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @ShellMethod(value = "Get all genre command", key = {"ga", "genreAll"})
    public List<Genre> getAll() {
        return genreService.getAll();
    }

    @ShellMethod(value = "Create genre command", key = {"gc", "genreCreate"})
    public Long create(@ShellOption String name) {
        return genreService.create(name);
    }

    @ShellMethod(value = "Read genre command", key = {"gr", "genreRead"})
    public Genre read(@ShellOption Long id) {
        return genreService.read(id);
    }

    @ShellMethod(value = "Update genre command", key = {"gu", "genreUpdate"})
    public void update(@ShellOption String name, @ShellOption Long id) {
        genreService.update(id, name);
    }

    @ShellMethod(value = "Delete genre command", key = {"gd", "genreDelete"})
    public void delete(@ShellOption Long id) {
        genreService.delete(id);
    }
}
