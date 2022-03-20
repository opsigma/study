package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("ru.otus.homework.domain")
@SpringBootApplication
public class HomeWork5 {
    public static void main(String[] args) {
        SpringApplication.run(HomeWork5.class);
    }
}
