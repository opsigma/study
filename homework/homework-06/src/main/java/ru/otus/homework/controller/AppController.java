package ru.otus.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework.service.shell.AppServiceSimple;

@ShellComponent
@RequiredArgsConstructor
public class AppController {
    private final AppServiceSimple appServiceSimple;

    @ShellMethod(value = "Stop application", key = {"sa", "stopApplication"})
    public void stopApplication() {
        appServiceSimple.stopApplication();
    }
}
