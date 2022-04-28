package ru.otus.homework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.book.Comment;
import ru.otus.homework.service.book.CommentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @ShellMethod(value = "Get all comment command", key = {"ca", "commentAll"})
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @ShellMethod(value = "Create comment command", key = {"cc", "commentCreate"})
    public Comment create(@ShellOption Long bookId, @ShellOption String comment) {
        return commentService.create(bookId, comment);
    }

    @ShellMethod(value = "Read comment command", key = {"cr", "commentRead"})
    public Comment read(@ShellOption Long id) {
        return commentService.read(id);
    }

    @ShellMethod(value = "Update Comment command", key = {"cu", "commentUpdate"})
    public void update(@ShellOption Long id,@ShellOption Long bookId, @ShellOption String comment) {
        commentService.update(id, bookId, comment);
    }

    @ShellMethod(value = "Delete comment command", key = {"cd", "commentDelete"})
    public void delete(@ShellOption Long id) {
        commentService.delete(id);
    }

}
