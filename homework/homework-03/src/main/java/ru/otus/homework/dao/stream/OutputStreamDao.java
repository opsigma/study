package ru.otus.homework.dao.stream;

public interface OutputStreamDao {

    void outputLine(String line);

    void outputFormat(String formatString,Object ... args);

    void close();
}
