package ru.otus.homework.service.quiz;

import ru.otus.homework.domain.quiz.Answer;
import ru.otus.homework.domain.quiz.Question;
import ru.otus.homework.domain.quiz.Quiz;

import java.util.List;

public interface QuizService {

    public Quiz startQuiz();

    public List<Answer> askQuestions();

    public Answer ask(Question question);

    public void calculateScore(Quiz quiz);
}
