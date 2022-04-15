package ru.otus.homework.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.author.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {


    @Override
    public List<Author> getAll() {
        return null;
    }

    @Override
    public Long create(String name) {
        return null;
    }

    @Override
    public Author read(long id) {
        return null;
    }

    @Override
    public void update(long id, String authorName) {

    }

    @Override
    public void delete(long id) {

    }
}
