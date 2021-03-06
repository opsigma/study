package ru.otus.homework.repository.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.genre.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public List<Genre> getAllByName(String name) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g " +
                "where g.name = :name ", Genre.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public Genre save(Genre genre) {
        if (Objects.isNull(genre.getId())) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Genre getById(Long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public void deleteById(Long id) {
        Genre genre = em.find(Genre.class, id);
        em.remove(genre);
    }
}
