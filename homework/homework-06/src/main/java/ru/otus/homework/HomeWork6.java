package ru.otus.homework;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.SQLException;


@EntityScan("ru.otus.homework.domain")
@SpringBootApplication
public class HomeWork6 {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(HomeWork6.class);
        Console.main(args);
    }
}
