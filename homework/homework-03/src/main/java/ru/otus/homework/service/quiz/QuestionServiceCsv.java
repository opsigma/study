package ru.otus.homework.service.quiz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.quiz.QuestionDaoCsv;
import ru.otus.homework.domain.quiz.Question;
import ru.otus.homework.service.locale.LocaleServiceSimple;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceCsv implements QuestionService {

    private final QuestionDaoCsv questionDaoCsv;
    private final LocaleServiceSimple localeService;

    private static final String C_QUESTION_EMPTY = "strings.app.service.quiz.questionEmpty";

    @Override
    public List<Question> getQuestions() {
        final List<Question> questions = questionDaoCsv.loadQuestions();
        if(questions.isEmpty()){
            log.info(localeService.getMessage(C_QUESTION_EMPTY));
        }
        return questions;
    }
}
