package ru.otus.homework.dao.stream;

import org.springframework.stereotype.Component;

import java.io.PrintStream;

@Component
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
