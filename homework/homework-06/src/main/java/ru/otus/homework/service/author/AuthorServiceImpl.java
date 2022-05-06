package ru.otus.homework.service.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.repository.author.AuthorRepositoryJpa;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJpa authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    @Override
    public List<Author> getByName(String name) {
        return authorRepository.getByName(name);
    }

    @Override
    @Transactional
    public Author create(String name) {
        return authorRepository.save(Author.builder().name(name).build());
    }

    @Override
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
