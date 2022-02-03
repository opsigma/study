package ru.otus.homework.dao.stream;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Component
public class InputStreamDaoScanner implements InputStreamDao {

    private final Scanner scanner;

    public InputStreamDaoScanner() {
        scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
