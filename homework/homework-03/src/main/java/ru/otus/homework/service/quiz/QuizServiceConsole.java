package ru.otus.homework.service.quiz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.stream.InputStreamDaoScanner;
import ru.otus.homework.dao.stream.OutputStreamDaoPrintStream;
import ru.otus.homework.domain.quiz.Answer;
import ru.otus.homework.domain.quiz.Option;
import ru.otus.homework.domain.quiz.Question;
import ru.otus.homework.domain.quiz.Quiz;
import ru.otus.homework.service.locale.LocaleServiceSimple;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizServiceConsole implements QuizService {

    private final InputStreamDaoScanner inputStream;
    private final OutputStreamDaoPrintStream outputStream;
    private final QuestionServiceCsv questionServiceCsv;
    private final LocaleServiceSimple localeService;

    private static final String C_ANSWER_CANNOT_BE_EMPTY = "strings.app.service.quiz.answerCannotBeEmpty";

    @Override
    public Quiz startQuiz() {
        return new Quiz();
    }

    @Override
    public List<Answer> askQuestions() {
        return questionServiceCsv.getQuestions().stream().map(this::ask).collect(Collectors.toList());
    }

    @Override
    public Answer ask(Question question) {
        outputStream.outputLine(question.getQuestion());

        AtomicInteger i = new AtomicInteger(1);
        final String options = question.getOptions().stream()
                .map(Option::getOption)
                .map(s -> String.format("%s) %s", i.getAndIncrement(), s))
                .collect(Collectors.joining("\n"));
        outputStream.outputLine(options);
        String line;
        while ((line = inputStream.readLine()).length() == 0) {
            outputStream.outputLine(localeService.getMessage(C_ANSWER_CANNOT_BE_EMPTY));
        }
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setAnswer(line);
        return answer;
    }

    @Override
    public void calculateScore(Quiz quiz) {
        quiz.getAnswers().forEach(a -> a.setScore(a.getQuestion().getCorrectAnswer().equals(a.getAnswer())));
    }
}
