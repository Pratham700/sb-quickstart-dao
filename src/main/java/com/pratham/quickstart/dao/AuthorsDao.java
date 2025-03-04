package com.pratham.quickstart.dao;

import com.pratham.quickstart.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorsDao {
    void create(Author author);
    Optional<Author> findOne(long id);
    List<Author> find();
    void update(Author author);

    void delete(long id);
}
