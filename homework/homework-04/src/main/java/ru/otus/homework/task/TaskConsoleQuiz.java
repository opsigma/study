package ru.otus.homework.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.homework.dao.stream.OutputStreamDaoPrintStream;
import ru.otus.homework.domain.quiz.Quiz;
import ru.otus.homework.service.locale.LocaleServiceSimple;
import ru.otus.homework.service.quiz.QuizServiceConsole;
import ru.otus.homework.service.user.UserServiceConsole;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskConsoleQuiz implements Task {

    private final QuizServiceConsole quizServiceConsole;
    private final UserServiceConsole userServiceConsole;
    private final OutputStreamDaoPrintStream outputStream;
    private final LocaleServiceSimple localeService;

    private static final String C_WELCOME_QUIZ = "strings.app.task.welcome";
    private static final String C_START_QUIZ = "strings.app.task.start";
    private static final String C_END_QUIZ = "strings.app.task.end";

    @Override
    public void start() {
        outputStream.outputLine(localeService.getMessage(C_WELCOME_QUIZ));
        final Quiz quiz = quizServiceConsole.startQuiz();
        quiz.setUser(userServiceConsole.getUser());
        outputStream.outputLine(localeService.getMessage(C_START_QUIZ));
        quiz.setAnswers(quizServiceConsole.askQuestions());
        quizServiceConsole.calculateScore(quiz);
        outputStream.outputFormat(localeService.getMessage(C_END_QUIZ)
                , quiz.getUser().getSurname()
                , quiz.getUser().getName()
                , quiz.getAnswers().stream().filter(answer -> Boolean.TRUE.equals(answer.getScore())).count());
    }

}
