package ru.otus.homework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.task.TaskConsoleQuiz;

@Slf4j
@ComponentScan
@Configuration
public class Application {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.register(Application.class);
        context.refresh();
        final TaskConsoleQuiz taskQuiz = context.getBean(TaskConsoleQuiz.class);
        taskQuiz.start();
        context.close();
    }
}
