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
import java.util.Objects;

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
                        "where c.book = :book "
                , Comment.class);
        query.setParameter("book", book);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Comment save(@NonNull Comment comment) {
        if (Objects.isNull(comment.getId())) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(Long id) {
        return em.find(Comment.class, id);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }
}
