package com.pratham.quickstart.dao;

import com.pratham.quickstart.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BoooksDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> find();

    void update(Book book);

    void delete(String isbn);
}
