package ru.otus.homework.repository.author;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework.domain.author.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
@DisplayName("Репозиторий для работы с авторами должен ")
class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;

    private Author existingAuthor1;
    private Author existingAuthor2;

    @BeforeEach
    void setUp() {
        existingAuthor1 = Author.builder().id(1L).name("author1").build();
        existingAuthor2 = Author.builder().id(2L).name("author2").build();
    }

    @Test
    @DisplayName("возвращать всех авторов из БД")
    void shouldReturnAllAuthors() {
        List<Author> expectedAuthors = List.of(existingAuthor1, existingAuthor2);
        List<Author> actualAuthors = authorRepository.findAll();
        assertThat(actualAuthors).usingRecursiveComparison().isEqualTo(expectedAuthors);
    }

    @Test
    @DisplayName("возвращать всех авторов из БД по имени")
    void shouldReturnAllAuthorsByName() {
        List<Author> expectedAuthors = List.of(existingAuthor1);
        List<Author> actualAuthors = authorRepository.findByName(existingAuthor1.getName());
        assertThat(actualAuthors).usingRecursiveComparison().isEqualTo(expectedAuthors);
    }

    @Test
    @DisplayName("записывать нового автора в БД")
    void createNewAuthor() {
        Author actualAuthor = authorRepository.save(Author.builder().name("new Author").build());
        Author expectedAuthor = Author.builder().id(3L).name("new Author").build();
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }


    @Test
    @DisplayName("обновлять автора в БД, если он там существует")
    void updateExistingAuthor() {
        existingAuthor1.setName("new name");
        Author expectedAuthor = authorRepository.save(existingAuthor1);
        Author actualAuthor = em.find(Author.class, existingAuthor1.getId());
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    @DisplayName("удалять по ИД автора из БД, если он там существует")
    void shouldCorrectDeleteAuthorById() {
        long id = 2L;
        authorRepository.deleteById(id);
        assertThatCode(() -> em.find(Author.class, id)).isNull();
    }
}