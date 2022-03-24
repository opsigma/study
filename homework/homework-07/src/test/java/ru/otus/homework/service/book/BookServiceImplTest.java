package ru.otus.homework.service.book;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.homework.dao.author.AuthorDao;
import ru.otus.homework.dao.book.BookDao;
import ru.otus.homework.dao.genre.GenreDao;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.service.locale.LocaleService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class BookServiceImplTest {

    @MockBean
    private BookDao bookDao;
    @MockBean
    private AuthorDao authorDao;
    @MockBean
    private GenreDao genreDao;
    @MockBean
    private LocaleService localeService;
    
    @ComponentScan("ru.otus.homework.dao")
    static class Config{
        
    }

    @Test
    void example() {

        Mockito.when(authorDao.getById(1)).thenReturn(Author.builder().id(1L).name("author").build());
        Author byId = authorDao.getById(1);
        assertEquals(byId.getId(), Long.valueOf(1L));
    }
}