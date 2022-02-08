package ru.otus.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.service.shell.ShellServiceSimple;

@ShellComponent
@RequiredArgsConstructor
public class ShellController {
    private final ShellServiceSimple shellServiceSimple;

    @ShellMethod(value = "Start quiz", key = {"sq", "startQuiz"})
    public String startQuiz() {
        return shellServiceSimple.startQuiz();
    }

    @ShellMethod(value = "Echo is a command that outputs the strings that are passed to it as arguments", key = {"e", "echo"})
    public String echo(@ShellOption String value) {
        return shellServiceSimple.echo(value);
    }

    @ShellMethod(value = "Stop application", key = {"sa", "stopApplication"})
    public void stopApplication() {
        shellServiceSimple.stopApplication();
    }
}
