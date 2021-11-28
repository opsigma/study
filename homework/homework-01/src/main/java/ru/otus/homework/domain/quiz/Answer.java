package ru.otus.homework.domain.quiz;

import lombok.Data;

import java.util.Optional;

@Data
public class Answer {
    private static final String ERROR_ANSWER_IS_EMPTY = "Правильный ответ не может быть пустым";
    private String answer;
    private Question question;
    private Boolean score = null;

    public boolean check() {
        score = Optional.ofNullable(question)
                .map(Question::getCorrectAnswer)
                .map(correctAnswer -> correctAnswer.equals(answer))
                .orElseThrow(() -> new IllegalArgumentException(ERROR_ANSWER_IS_EMPTY));
        return score;
    }
}
