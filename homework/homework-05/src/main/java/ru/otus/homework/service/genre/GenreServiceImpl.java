package ru.otus.homework.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.genre.GenreDao;
import ru.otus.homework.domain.genre.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public Long create(String name) {
        Genre genre = Genre.builder().name(name).build();
        return genreDao.insert(genre);
    }

    @Override
    public Genre read(Long id) {
        return genreDao.getById(id);
    }

    @Override
    public void update(Long id, String name) {
        Genre genre = Genre.builder().id(id).name(name).build();
        genreDao.update(genre);
    }

    @Override
    public void delete(Long id) {
        genreDao.deleteById(id);
    }
}
