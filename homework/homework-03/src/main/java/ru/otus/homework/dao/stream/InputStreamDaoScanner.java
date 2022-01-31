package ru.otus.homework.dao.stream;

import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Repository
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
