package ru.otus.homework.service.quiz;

import ru.otus.homework.domain.quiz.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions();
}
