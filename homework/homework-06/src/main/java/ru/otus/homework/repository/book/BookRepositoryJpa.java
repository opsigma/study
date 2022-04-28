package ru.otus.homework.repository.book;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.book.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Book save(@NonNull Book book) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void update(Long id, String name, Long authorId, Long genreId) {

    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }
}
