package ru.otus.homework;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.task.TaskConsoleQuiz;

@Slf4j
public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/context/spring-context.xml");
        final TaskConsoleQuiz taskQuiz = context.getBean(TaskConsoleQuiz.class);

        taskQuiz.start();
        context.close();
    }
}
