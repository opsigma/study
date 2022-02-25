package ru.otus.homework.service.shell;

public interface ConsoleService {
    public String getUserName();

    public String getUserSurname();

    public String getInfo(String lineString, String defaultValue);
}
