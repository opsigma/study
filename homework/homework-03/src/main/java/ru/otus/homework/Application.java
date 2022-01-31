package ru.otus.homework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.homework.task.TaskConsoleQuiz;

@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        final TaskConsoleQuiz taskConsoleQuiz = context.getBean(TaskConsoleQuiz.class);
        taskConsoleQuiz.start();
    }

}
