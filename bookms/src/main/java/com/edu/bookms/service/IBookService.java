package com.edu.bookms.service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.edu.bookms.model.Book;

public interface IBookService {

    Collection<Book> findAll();

    Optional<Book> findById(Integer id);

    Optional<Book> findByIsbn(String isbn);

    boolean delete(Integer id);
}
