package ru.otus.homework.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.repository.author.AuthorRepository;
import ru.otus.homework.repository.author.AuthorRepositoryJpa;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJpa authorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    @Transactional
    public Long create(String name) {
        return authorRepository.save(Author.builder().name(name).build()).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Author read(long id) {
        return authorRepository.getById(id);
    }

    @Override
    @Transactional
    public void update(long id, String authorName) {
        authorRepository.save(Author.builder().id(id).name(authorName).build());
    }

    @Override
    @Transactional
    public void delete(long id) {
        authorRepository.deleteById(id);
    }
}
