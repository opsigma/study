package ru.otus.homework.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.domain.quiz.Answer;
import ru.otus.homework.domain.quiz.Question;
import ru.otus.homework.domain.quiz.Quiz;
import ru.otus.homework.domain.user.User;
import ru.otus.homework.service.QuestionServiceCsv;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class TaskConsoleQuiz implements Task {

    private static final String WELCOME_QUIZ = "Welcome test";
    private static final String START_QUIZ = "Begin test";
    private static final String ENTER_NAME = "Enter Name";
    private static final String ENTER_SURNAME = "Enter Surname";
    private static final String END_QUIZ = "Result test";
    private final QuestionServiceCsv questionServiceCsv;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {
        try {

            System.out.printf("%s\n", WELCOME_QUIZ);
            final Quiz quiz = new Quiz();
            quiz.setUser(getUser());
            System.out.printf("%s:\n", START_QUIZ);
            quiz.setAnswers(askQuestions());
            System.out.printf("%s: %s", END_QUIZ, quiz.calculateScore());
        } finally {
            scanner.close();
        }
    }

    private User getUser() {
        final User user = new User();
        user.setName(Optional.ofNullable(getInfo(ENTER_NAME)).orElse("TEST_NAME"));
        user.setSurname(Optional.ofNullable(getInfo(ENTER_SURNAME)).orElse("TEST_SURNAME"));
        return user;
    }

    private String getInfo(String lineString) {
        System.out.printf("%s:\n", lineString);
        return readLine();
    }

    private List<Answer> askQuestions() {
        final List<Question> questions = questionServiceCsv.getQuestions();
        final List<Answer> answers = new ArrayList<>();
        for (Question question : questions) {
            answers.add(ask(question));
        }
        return answers;
    }

    private Answer ask(Question question) {
        System.out.println(question.getQuestion());
        final String line = readLine();
        final Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setAnswer(line);
        return answer;
    }

    private String readLine() {
        return scanner.nextLine();
    }
}
