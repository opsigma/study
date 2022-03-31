package ru.otus.homework.domain.author;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    private Long id;
    private String name;
}
