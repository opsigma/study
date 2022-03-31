package ru.otus.homework.domain.book;


import lombok.Builder;
import lombok.Data;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.domain.genre.Genre;


@Data
@Builder
public class Book {
    private Long id;
    private String name;
    private Author author;
    private Genre genre;
}
