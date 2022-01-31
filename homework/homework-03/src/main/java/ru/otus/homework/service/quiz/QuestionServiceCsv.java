package ru.otus.homework.service.quiz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.quiz.QuestionDaoCsv;
import ru.otus.homework.domain.quiz.Question;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceCsv implements QuestionService {

    private static final String QUESTION_EMPTY = "Empty list of questions";
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
