package ru.otus.homework.service.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.genre.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    @Override
    public List<Genre> getAll() {
        return null;
    }

    @Override
    public Long create(String name) {
        return null;
    }

    @Override
    public Genre read(Long id) {
        return null;
    }

    @Override
    public void update(Long id, String name) {

    }

    @Override
    public void delete(Long id) {

    }
}
