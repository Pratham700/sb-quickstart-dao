package com.pratham.quickstart.dao.impl;

import com.pratham.quickstart.dao.AuthorsDao;
import com.pratham.quickstart.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AutohorDaoImpl implements AuthorsDao {

    private JdbcTemplate jdbcTemplate;

    public AutohorDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("INSERT INTO authors (id, name, age) VALUES (?, ?)", author.getId(), author.getName(), author.getAge());
    }

    @Override
    public Optional<Author> findOne(long id) {
        List<Author> results = jdbcTemplate.query("SELECT  id, name, age FROM authors WHERE id = ? LIMIT 1", new AuthorRowMapper(), id);

        return results.stream().findFirst();
    }

    @Override
    public List<Author> find() {
        return jdbcTemplate.query("SELECT id, name, age FROM authors", new AuthorRowMapper());
    }

    @Override
    public void update(Author author) {
        jdbcTemplate.update("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"
                , author.getId(), author.getName(), author.getAge(), author.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM authors WHERE id = ?", id);
    }

    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public  Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
