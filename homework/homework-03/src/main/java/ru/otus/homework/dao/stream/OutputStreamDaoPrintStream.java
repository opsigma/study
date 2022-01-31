package ru.otus.homework.dao.stream;

import org.springframework.stereotype.Repository;

import java.io.PrintStream;

@Repository
public class OutputStreamDaoPrintStream implements OutputStreamDao {

    private final PrintStream out;

    public OutputStreamDaoPrintStream() {
        out = System.out;
    }

    @Override
    public void outputLine(String line) {
        out.println(line);
    }

    @Override
    public void outputFormat(String formatString, Object ... args) {
        out.printf(formatString,args);
    }

    @Override
    public void close() {
        out.close();
    }
}
