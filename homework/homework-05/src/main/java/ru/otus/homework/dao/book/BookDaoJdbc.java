package ru.otus.homework.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.author.Author;
import ru.otus.homework.domain.book.Book;
import ru.otus.homework.domain.genre.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private static final String bookIdColumnName = "id";
    private static final String bookNameColumnName = "bookName";
    private static final String authorIdColumnName = "authorId";
    private static final String authorNameColumnName = "authorName";
    private static final String genreIdColumnName = "genreId";
    private static final String genreNameColumnName = "genreName";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Book> getAll() {
        String sql = String.format("select " +
                        "b.id as %s, b.name as %s, " +
                        "a.id as %s, a.name as %s, " +
                        "g.id as %s, g.name as %s " +
                        "from book b " +
                        "left join author a on a.id = b.author_id " +
                        "left join genre g on g.id = b.genre_id"
                , bookIdColumnName, bookNameColumnName
                , authorIdColumnName, authorNameColumnName
                , genreIdColumnName, genreNameColumnName
        );
        return jdbcTemplate.query(sql, new BookMapper());
    }

    @Override
    public long insert(Book book) {
        MapSqlParameterSource map = getParameterMap(book);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("insert into book (name, author_id, genre_id) values ( :name, :author_id, :genre_id)", map, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Book getById(Long id) {
        String sql = String.format("select " +
                        "b.id as %s, b.name as %s, " +
                        "a.id as %s, a.name as %s, " +
                        "g.id as %s, g.name as %s " +
                        "from book b " +
                        "left join author a on a.id=b.author_id " +
                        "left join genre g on g.id=b.genre_id " +
                        "where b.id = :id"
                , bookIdColumnName, bookNameColumnName
                , authorIdColumnName, authorNameColumnName
                , genreIdColumnName, genreNameColumnName
        );

        return jdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), new BookMapper());
    }

    @Override
    public void update(Book book) {
        MapSqlParameterSource map = getParameterMap(book);
        map.addValue("id", Optional.of(book).map(Book::getId).orElse(null));
        jdbcTemplate.update("update book set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id", map);
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcTemplate.update("delete from book where id = :id", params);
    }

    private MapSqlParameterSource getParameterMap(Book book) {
        Optional<Book> optionalBook = Optional.of(book);
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", optionalBook.map(Book::getName).orElse(null));
        map.addValue("author_id", optionalBook.map(Book::getAuthor).map(Author::getId).orElse(null));
        map.addValue("genre_id", optionalBook.map(Book::getGenre).map(Genre::getId).orElse(null));
        return map;
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long authorId = rs.getObject(authorIdColumnName, Long.class);
            String authorName = rs.getString(authorNameColumnName);
            Author author = Author.builder().id(authorId).name(authorName).build();
            Long genreId = rs.getObject(genreIdColumnName, Long.class);
            String genreName = rs.getString(genreNameColumnName);
            Genre genre = Genre.builder().id(genreId).name(genreName).build();
            Long id = rs.getObject(bookIdColumnName, Long.class);
            String name = rs.getString(bookNameColumnName);
            return Book.builder()
                    .id(id)
                    .name(name)
                    .author(author)
                    .genre(genre)
                    .build();
        }
    }
}
