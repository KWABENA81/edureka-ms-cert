package com.edu.bookms.service;


import java.util.Collection;
import java.util.Optional;

import com.edu.bookms.model.Book;

public interface IBookService {

    Collection<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByIsbn(String isbn);

    void delete(Long id);
}
