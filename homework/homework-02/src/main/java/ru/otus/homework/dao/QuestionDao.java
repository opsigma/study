package ru.otus.homework.dao;

import ru.otus.homework.domain.quiz.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> loadQuestions();

}
