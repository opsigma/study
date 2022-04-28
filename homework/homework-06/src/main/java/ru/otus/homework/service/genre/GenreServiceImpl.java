package ru.otus.homework.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.genre.Genre;
import ru.otus.homework.repository.genre.GenreRepositoryJpa;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepositoryJpa genreRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return genreRepository.getAll();
    }

    @Override
    @Transactional
    public Genre create(String name) {
        Genre genre = Genre.builder().name(name).build();
        return genreRepository.save(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre read(Long id) {
        return genreRepository.getById(id);
    }

    @Override
    @Transactional
    public void update(Long id, String name) {
        Genre genre = Genre.builder().id(id).name(name).build();
        genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
