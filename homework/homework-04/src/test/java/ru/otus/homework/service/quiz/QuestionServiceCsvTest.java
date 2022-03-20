package ru.otus.homework.service.quiz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.homework.dao.quiz.QuestionDaoCsv;
import ru.otus.homework.domain.quiz.Option;
import ru.otus.homework.domain.quiz.Question;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = QuestionConfiguration.class)
class QuestionServiceCsvTest {

    @MockBean
    private QuestionDaoCsv questionDaoCsv;

    @Test
    public void getQuestions() {
        final Question qActual = new Question();
        qActual.setQuestion("question");
        qActual.setCorrectAnswer("answer");
        final Option op1 = new Option("option 1");
        final Option op2 = new Option("answer");
        qActual.setOptions(List.of(op1, op2));
        final List<Question> expected = List.of(qActual);
        Mockito.when(questionDaoCsv.loadQuestions()).thenReturn(expected);
        final List<Question> actual = questionDaoCsv.loadQuestions();
        Assertions.assertThat(actual).isEqualTo(expected);
    }

}