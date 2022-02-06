package ru.otus.homework.domain.quiz;

import lombok.Data;
import ru.otus.homework.domain.user.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class Quiz {
    private User user;
    private List<Answer> answers = new ArrayList<>();
}
