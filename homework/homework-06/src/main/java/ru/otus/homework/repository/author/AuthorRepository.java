package ru.otus.homework.repository.author;

import org.springframework.lang.NonNull;
import ru.otus.homework.domain.author.Author;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AuthorRepository {
    List<Author> getAll();

    Author save(Author author);

    Author getById(long id);

    void deleteById(long id);

}
