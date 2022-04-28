package ru.otus.homework.repository.book;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.book.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllByBook(Book book) {
        TypedQuery<Comment> query = em.createQuery("select c " +
                        "from Comment c " +
                        "where c.book=:book "
                , Comment.class);
        query.setParameter("book", book);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Comment save(@NonNull Comment comment) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(Long id) {
        return null;
    }


    @Override
    @Transactional
    public void deleteById(Long id) {

    }
}
