package ru.otus.homework.domain.quiz;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String question;
    private List<Option> options;
    private String correctAnswer;
}
