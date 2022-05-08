package ru.otus.homework.repository.author;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.domain.author.Author;

import java.util.List;

public interface AuthorRepository  extends JpaRepository<Author, Long> {

    List<Author> findByName(String name);

}
