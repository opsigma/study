package ru.otus.homework.service.author;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework.dao.author.AuthorDao;
import ru.otus.homework.domain.author.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Сервис для работы с автором должен")
@SpringBootTest
class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;
    
    @MockBean
    private AuthorDao authorDao;
    
    private Author existingAuthor1;
    private Author existingAuthor2;
    
    @BeforeEach
    void setUp() {
        existingAuthor1 = Author.builder().id(1L).name("author").build();
        existingAuthor2 = Author.builder().id(2L).name("author2").build();
    }

    @DisplayName("должен возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorList() {
        List<Author> expectedAuthorList =List.of(existingAuthor1, existingAuthor2);
        Mockito.when(authorDao.getAll()).thenReturn(expectedAuthorList);
        List<Author> actualBookList = authorDao.getAll();
        assertThat(actualBookList).usingRecursiveComparison().isEqualTo(expectedAuthorList);
    }

    @Test
    @DisplayName("создавать автора")
    void shouldCreateBook() {
        var expectedAuthor = Author.builder().name("new Author").build();
        authorService.create(expectedAuthor.getName());
        Mockito.verify(authorDao, Mockito.times(1)).insert(expectedAuthor);
    }

    @Test
    @DisplayName("возвращать автора по идентификатору")
    void shouldReturnBookById() {
        var expectedAuthor = existingAuthor1;
        Mockito.when(authorDao.getById(expectedAuthor.getId())).thenReturn(expectedAuthor);
        Author actualBook = authorService.read(existingAuthor1.getId());
        Assertions.assertThat(expectedAuthor)
                .usingRecursiveComparison()
                .isEqualTo(actualBook);
    }

    @Test
    @DisplayName("обновлять автора")
    void shouldUpdateBook() {
        var expectedAuthor = existingAuthor2;
        expectedAuthor.setName("update Author");
        authorService.update(expectedAuthor.getId(),expectedAuthor.getName());
        Mockito.verify(authorDao, Mockito.times(1)).update(expectedAuthor);
    }

    @Test
    @DisplayName("удалять автора по его идентификатору")
    void shouldDeleteBookById() {
        var expectedAuthor = existingAuthor2;
        authorService.delete(expectedAuthor.getId());
        Mockito.verify(authorDao, Mockito.times(1)).deleteById(expectedAuthor.getId());
    }
}
