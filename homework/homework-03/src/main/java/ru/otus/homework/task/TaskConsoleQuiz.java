package ru.otus.homework.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.homework.dao.stream.OutputStreamDaoPrintStream;
import ru.otus.homework.domain.quiz.Quiz;
import ru.otus.homework.service.quiz.QuizServiceConsole;
import ru.otus.homework.service.user.UserServiceConsole;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskConsoleQuiz implements Task {

    private static final String WELCOME_QUIZ = "Welcome!";
    private static final String START_QUIZ = "Begin test:";
    private static final String END_QUIZ = "%s %s, your result test: %s\n";

    private final QuizServiceConsole quizServiceConsole;
    private final UserServiceConsole userServiceConsole;
    private final OutputStreamDaoPrintStream outputStream;

    @Override
    public void start() {
        try {
            outputStream.outputLine(WELCOME_QUIZ);
            final Quiz quiz = quizServiceConsole.startQuiz();
            quiz.setUser(userServiceConsole.getUser());
            outputStream.outputLine(START_QUIZ);
            quiz.setAnswers(quizServiceConsole.askQuestions());
            quizServiceConsole.calculateScore(quiz);
            outputStream.outputFormat(END_QUIZ
                    , quiz.getUser().getSurname()
                    , quiz.getUser().getName()
                    , quiz.getAnswers().stream().filter(answer -> Boolean.TRUE.equals(answer.getScore())).count());
        } finally {
            outputStream.close();
        }
    }

}
