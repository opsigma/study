package ru.otus.homework.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.author.AuthorDao;
import ru.otus.homework.domain.author.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public Long create(String name) {
        Author author = Author.builder().name(name).build();
        return authorDao.create(author);
    }

    @Override
    public Author read(long id) {
        return authorDao.read(id);
    }

    @Override
    public void update(long id, String name) {
        Author author = Author.builder().id(id).name(name).build();
        authorDao.update(author);
    }

    @Override
    public void delete(long id) {
        authorDao.delete(id);
    }
}
