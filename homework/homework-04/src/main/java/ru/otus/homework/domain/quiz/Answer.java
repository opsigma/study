package ru.otus.homework.domain.quiz;

import lombok.Data;

@Data
public class Answer {
    private static final String ERROR_ANSWER_IS_EMPTY = "The correct answer cannot be empty";
    private String answer;
    private Question question;
    private Boolean score;
}
