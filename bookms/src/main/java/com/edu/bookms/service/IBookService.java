package com.edu.bookms.service;


import java.util.Collection;
import com.edu.bookms.model.Book;

public interface IBookService {

    Collection<Book> findAll();

    Book findById(Integer id);

    Book findByIsbn(String isbn);

    boolean delete(Integer id);
}
