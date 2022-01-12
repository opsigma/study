package ru.otus.homework.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.quiz.Answer;
import ru.otus.homework.domain.quiz.Option;
import ru.otus.homework.domain.quiz.Question;
import ru.otus.homework.domain.quiz.Quiz;
import ru.otus.homework.domain.user.User;
import ru.otus.homework.service.QuestionServiceCsv;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskConsoleQuiz implements Task {

    private static final String WELCOME_QUIZ = "Welcome!";
    private static final String START_QUIZ = "Begin test:";
    private static final String ENTER_NAME = "Enter Name:";
    private static final String ENTER_SURNAME = "Enter Surname:";
    private static final String END_QUIZ = "%s %s, your result test: %s\n";
    private static final String ANSWER_CANNOT_BE_EMPTY = "Warning. The answer cannot be empty.";
    private final QuestionServiceCsv questionServiceCsv;
    private final Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

    @Override
    public void start() {
        try {
            System.out.println(WELCOME_QUIZ);
            final Quiz quiz = new Quiz();
            quiz.setUser(getUser());
            System.out.println(START_QUIZ);
            quiz.setAnswers(askQuestions());
            calculateScore(quiz);
            System.out.printf(END_QUIZ
                    , quiz.getUser().getSurname()
                    , quiz.getUser().getName()
                    , quiz.getAnswers().stream().filter(answer -> Boolean.TRUE.equals(answer.getScore())).count());
        } finally {
            scanner.close();
        }
    }

    private User getUser() {
        final User user = new User();
        user.setName(getInfo(ENTER_NAME, "TEST_USER"));
        user.setSurname(getInfo(ENTER_SURNAME, "TEST_SURNAME"));
        return user;
    }

    private String getInfo(String lineString, String defaultValue) {
        System.out.println(lineString);
        return Optional.ofNullable(readLine()).filter(s -> s.length() > 0).orElse(defaultValue);
    }

    private List<Answer> askQuestions() {
        return questionServiceCsv.getQuestions().stream().map(this::ask).collect(Collectors.toList());
    }

    private Answer ask(Question question) {
        System.out.println(question.getQuestion());

        AtomicInteger i = new AtomicInteger(1);
        final String options = question.getOptions().stream()
                .map(Option::getOption)
                .map(s -> String.format("%s) %s", i.getAndIncrement(), s))
                .collect(Collectors.joining("\n"));
        System.out.println(options);
        String line;
        while ((line = readLine()).length() == 0) {
            System.out.println(ANSWER_CANNOT_BE_EMPTY);
        }
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setAnswer(line);
        return answer;
    }

    private void calculateScore(Quiz quiz) {
        quiz.getAnswers().forEach(a -> a.setScore(a.getQuestion().getCorrectAnswer().equals(a.getAnswer())));
    }

    private String readLine() {
        return scanner.nextLine();
    }
}
