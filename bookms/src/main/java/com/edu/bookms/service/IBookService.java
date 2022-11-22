package com.edu.bookms.service;

import com.edu.bookms.model.Book;

import java.util.Collection;


public interface IBookService {
    Collection<Book> findAll();

    Book findById(Integer id);

    Book findByIsbn(String isbn);

    boolean delete(Integer id);
}
