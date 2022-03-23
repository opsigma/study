package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDaoJdbc implements PersonDao {
    private final NamedParameterJdbcOperations jdbc;

    public PersonDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        String sql = "select count(*) from persons";
        return jdbc.queryForObject(sql, Map.of(), Integer.class);
    }

    @Override
    public void insert(Person person) {
        String sql = "insert into persons (id, name) values (? , ?)";
        jdbc.getJdbcOperations().update(sql, person.getId(), person.getName());
    }

    @Override
    public void update(Person person) {
        String sql = "update persons set name = ? where id = ? ";
        jdbc.getJdbcOperations().update(sql, person.getName(), person.getId());
    }

    @Override
    public void deleteById(long id) {
        String sql = "delete from persons  where id = :id ";
        jdbc.update(sql, Map.of("id", id));
    }

    @Override
    public Person getById(long id) {
        String sql = "select id, name from persons where id = :id";
        return jdbc.queryForObject(sql, Map.of("id", id), new PersonMapper());
    }

    @Override
    public List<Person> getAll() {
        String sql = "select id, name from persons";
        return jdbc.query(sql, new PersonMapper());
    }

    private static class PersonMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Person(rs.getLong("id"), rs.getString("name"));
        }
    }
}
