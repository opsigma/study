package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.homework.dao.QuestionDaoCsv;
import ru.otus.homework.domain.quiz.Question;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class QuestionServiceCsv implements QuestionService {

    private static final String QUESTION_EMPTY = "Пустой список вопросов";
    private final QuestionDaoCsv questionDaoCsv;
    
    @Override
    public List<Question> getQuestions() {
        final List<Question> questions = questionDaoCsv.loadQuestions();
        if(questions.isEmpty()){
            log.info(QUESTION_EMPTY);
        }
        return questions;
    }
}
