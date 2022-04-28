package ru.otus.homework.repository.author;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.author.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

//    @Override
//    @Transactional
//    public Author save(Author author) {
//        return null;
//    }


    @Override
    @Transactional
    public Author save(@NonNull Author author) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(long id) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(long id) {

    }
}
