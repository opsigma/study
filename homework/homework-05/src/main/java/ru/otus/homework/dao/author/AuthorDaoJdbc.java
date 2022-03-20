package ru.otus.homework.dao.author;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.author.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private static final String authorIdColumnName = "authorId";
    private static final String authorNameColumnName = "authorName";

    private final NamedParameterJdbcOperations jdbcTemplate;

    @Override
    public List<Author> getAll() {
        String sql = String.format("select " +
                        "a.id as %s, a.name as %s " +
                        "from author a "
                , authorIdColumnName, authorNameColumnName
        );
        return jdbcTemplate.query(sql, new AuthorMapper());
    }

    @Override
    public long create(Author author) {
        MapSqlParameterSource map = getParameterMap(author);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("insert into author (name) values ( :name)", map, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Author read(long id) {
        String sql = String.format("select " +
                        "a.id as %s, a.name as %s " +
                        "from author a " +
                        "where a.id = :id"
                , authorIdColumnName, authorNameColumnName
        );
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, Collections.singletonMap("id", id), new AuthorMapper()));
    }

    @Override
    public void update(Author author) {
        MapSqlParameterSource map = getParameterMap(author);
        map.addValue("id", Optional.of(author).map(Author::getId).orElse(null));
        jdbcTemplate.update("update author set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id", map);
    }

    @Override
    public void delete(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcTemplate.update("delete from author where id = :id", params);
    }

    private MapSqlParameterSource getParameterMap(Author author) {
        Optional<Author> optionalAuthor = Optional.of(author);
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", optionalAuthor.map(Author::getName).orElse(null));
        return map;
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong(authorIdColumnName);
            String name = rs.getString(authorNameColumnName);
            return Author.builder().id(id).name(name).build();
        }
    }
}
