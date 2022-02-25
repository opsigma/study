package ru.otus.homework.service.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.task.TaskConsoleQuiz;

@Service
@RequiredArgsConstructor
public class ShellServiceSimple implements ShellService {
    private final TaskConsoleQuiz taskConsoleQuiz;

    @Override
    public String startQuiz() {
        taskConsoleQuiz.start();
        return "";
    }

    @Override
    public String echo(String value) {
        return value;
    }

    @Override
    public void stopApplication() {
        System.exit(1);
    }
}
