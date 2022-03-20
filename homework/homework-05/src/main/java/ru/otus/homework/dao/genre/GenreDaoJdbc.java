package ru.otus.homework.dao.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
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
public class GenreDaoJdbc implements GenreDao {

    private static final String genreIdColumnName = "genreId";
    private static final String genreNameColumnName = "genreName";

    private final NamedParameterJdbcOperations jdbcTemplate;

    @Override
    public List<Genre> getAll() {
        String sql = String.format("select " +
                        "a.id as %s, a.name as %s " +
                        "from genre a "
                , genreIdColumnName, genreNameColumnName
        );
        return jdbcTemplate.query(sql, new GenreMapper());
    }

    @Override
    public long create(Genre genre) {
        MapSqlParameterSource map = getParameterMap(genre);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("insert into genre (name) values ( :name)", map, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Genre read(Long id) {
        String sql = String.format("select " +
                        "a.id as %s, a.name as %s " +
                        "from genre a " +
                        "where a.id = :id"
                , genreIdColumnName, genreNameColumnName
        );
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql, Collections.singletonMap("id", id), new GenreMapper()));
    }

    @Override
    public void update(Genre genre) {
        MapSqlParameterSource map = getParameterMap(genre);
        map.addValue("id", Optional.of(genre).map(Genre::getId).orElse(null));
        jdbcTemplate.update("update genre set name = :name where id = :id", map);
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbcTemplate.update("delete from genre where id = :id", params);
    }

    private MapSqlParameterSource getParameterMap(Genre genre) {
        Optional<Genre> optionalAuthor = Optional.of(genre);
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", optionalAuthor.map(Genre::getName).orElse(null));
        return map;
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong(genreIdColumnName);
            String name = rs.getString(genreNameColumnName);
            return Genre.builder().id(id).name(name).build();
        }
    }
}
